package edu.systemia.auditing_entities.infrastructure.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {

	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
		// new Phrase("Top Left"), 30, 800, 0);
		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
		// new Phrase("Top Right"), 550, 800,
		// 0);
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
		// new Phrase("http://www.xxxx-your_example.com/"), 110, 30, 0);
		// ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
		// new Phrase("pag. " + document.getPageNumber()), 550, 30, 0);
		var font = new Font();
		font.setSize(11);
		font.setStyle(Font.BOLDITALIC);
		
		var text = new Phrase("pag. " + document.getPageNumber(), font);
		PdfContentByte cb = writer.getDirectContent();
		Rectangle rect = writer.getPageSize();
		float x = rect.getRight() - document.rightMargin();
		float y = rect.getBottom() + 50;
		ColumnText.showTextAligned(cb, Element.ALIGN_RIGHT, text, x, y, 0);
	}

}
