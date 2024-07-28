package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "BLOG_AUTHORS")
@EntityListeners(AuditingEntityListener.class)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHORS_SEQUENCE")
    @SequenceGenerator(name = "AUTHORS_SEQUENCE", sequenceName = "BLOG_AUTHOR_SEQ", allocationSize = 1)
    @Column(name = "AT_ID", precision = 19)
    private Long id;

    @Column(name = "AT_FIRSTNAME", length = 100)
    private String firstname;

    @Column(name = "AT_LASTNAME", length = 100)
    private String lastname;

    @Column(nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

//	@ManyToMany
//	@JoinTable(name = "COURSE_AUTHORS", 
//		joinColumns = @JoinColumn(name = "AT_ID"), 
//		inverseJoinColumns = @JoinColumn(name = "CO_ID")
//	)
//	Set<Course> courses;
    @OneToMany(mappedBy = "author")
    private List<Subscription> subscriptions;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<Note> notes;
}
