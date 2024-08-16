package edu.systemia.auditing_entities.domain.services.adapaters;

import edu.systemia.auditing_entities.domain.services.AuthorService;
import edu.systemia.auditing_entities.infrastructure.dto.AuthorQueryResult;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.repository.AuthorRepository;
import edu.systemia.auditing_entities.infrastructure.utils.HeaderFooterPageEvent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static edu.systemia.auditing_entities.infrastructure.persistence.repository.specs.AuthorSpec.withFirstname;

@Service
@RequiredArgsConstructor
public class AuthorServiceAdapter implements AuthorService {

	@PersistenceContext
	private final EntityManager entityManager;
	private final AuthorRepository authorRepository;

	@Override
	public Page<AuthorQueryResult> paginateAuthor(Pageable pageable, String filterFirstname) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AuthorQueryResult> query = cb.createQuery(AuthorQueryResult.class);
		Root<Author> authorRoot = query.from(Author.class);

		var predicate = withFirstname(filterFirstname).toPredicate(authorRoot, query, cb);
		query.where(predicate);

		if (pageable.getSort().isSorted()) {
			var orders = new ArrayList<Order>();
			for (Sort.Order order : pageable.getSort()) {
				orders.add(order.isAscending() ? cb.asc(authorRoot.get(order.getProperty()))
						: order.isDescending() ? cb.desc(authorRoot.get(order.getProperty())) : null);
			}
			query.orderBy(orders);
		}

		query.select(cb.construct(AuthorQueryResult.class, authorRoot.get("id"), authorRoot.get("firstname")));

		var typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());
		var resultList = typedQuery.getResultList();

		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Author> countRoot = countQuery.from(Author.class);
		countQuery.select(cb.count(countRoot));
		countQuery.where(predicate);

		return new PageImpl<>(resultList, pageable, resultList.size());
	}

	@Override
	public ByteArrayResource exportPdf() throws IOException, DocumentException {

		var authors = authorRepository.findAll();

		String authorSummary = authors.stream().map(author -> MessageFormat.format("{0} {1} {2}", author.getFirstname(),
			author.getLastname(), author.getActive())).collect(Collectors.joining(", "));

		var classPathResource = new ClassPathResource("/static/images/oracle_logo.png");
		byte[] allBytes = Files.readAllBytes(classPathResource.getFile().toPath());

		ByteArrayOutputStream out = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, out);
		writer.setPageEvent(new HeaderFooterPageEvent());
		document.setMargins(60, 60, 70, 70);

		var fontBold = new Font();
		fontBold.setStyle(Font.BOLDITALIC);
		fontBold.setColor(BaseColor.RED);

		document.open();

		// Header header = new Header("My Document", "1234-----");
		// document.add(header);

		int numPage = writer.getCurrentPageNumber();

		if (numPage == 1) {
			Image image;
			image = Image.getInstance(allBytes);
			image.scaleAbsolute(200, 150);
			image.setBorderWidth(2);
			image.setBorder(1);
			image.setBorderColor(BaseColor.MAGENTA);
			document.add(image);

			var subtitle = new Paragraph("Summary all Users", fontBold);
			document.add(subtitle);

			var p1 = new Paragraph("\nJOSÉ GABRIEL TANTA CALDERÓN\n\n");
			p1.setAlignment(Element.ALIGN_RIGHT);
			document.add(p1);
		}

		var fontParagraph = new Font();
		fontParagraph.setStyle(Font.NORMAL);
		fontParagraph.setFamily(FontFamily.HELVETICA.name());
		
		var fontParagraphTitle = new Font();
		fontParagraphTitle.setStyle(Font.BOLD);
		fontParagraphTitle.setFamily(FontFamily.HELVETICA.name());
		
		for (int i = 0; i < 6; i++) {
			var title = new Phrase("MI-TITLE\u00A0\u00A0\u00A0", fontParagraphTitle);
			var summary = new Phrase(authorSummary, fontParagraph);
			
			var p3 = new Paragraph();
			p3.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
			p3.add(title);
			p3.add(summary);
			p3.add(Chunk.NEWLINE);
			p3.add(Chunk.NEWLINE);
			document.add(p3);
		}

		// var p2 = new Paragraph(authorSummary, fontBold);
		// p2.setFirstLineIndent(20);
		// p2.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
		// document.add(p2);

		// document.newPage();
		// var p3 = new Paragraph(authorSummary, fontBold);
		// p3.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
		// document.add(p3);

		// document.newPage();
		// var p4 = new Paragraph(MessageFormat.format("Hola {0}", "JOSÉ GABRIEL TANTA
		// CALDERÓN"), fontBold);
		// document.add(p4);

		document.close();
		out.close();
		return new ByteArrayResource(out.toByteArray());
	}

}
