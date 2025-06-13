package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "BLOG_DOCS")

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BlogDoc implements Serializable {

	@Serial
	private static final long serialVersionUID = -8623165106982224011L;

	/**
	 * dcId
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "BLOG_DOCS_SEQUENCE_GEN")
	@SequenceGenerator(name = "BLOG_DOCS_SEQUENCE_GEN", sequenceName = "BLOG_DOCS_SEQ", allocationSize = 1)
	@Column(name = "DC_ID", precision = 19, unique = true, updatable = false)
	private Integer dcId;

	/**
	 * dcData
	 */
	@Basic(fetch = FetchType.LAZY)
	@Lob // Used for large objects like BLOBs
	@Column(name = "DC_DATA", nullable = false)
	private byte[] dcData; // DC_DATA converted to dcData, stored as byte array

	/**
	 * dcFilename
	 */
	@Column(name = "DC_FILENAME", nullable = false, length = 240)
	private String dcFilename; // DC_FILENAME converted to dcFilename

	/**
	 * blogCourse
	 */
	@ManyToOne // Many BlogDocs can belong to one BlogCourse
	@JoinColumn(name = "DC_ID_COURSE", referencedColumnName = "CO_ID")
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	private Course blogCourse; // DC_ID_COURSE converted to blogCourse (entity relationship)
}
