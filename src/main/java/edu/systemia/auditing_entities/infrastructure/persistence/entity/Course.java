package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BLOG_COURSES")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSES_SEQUENCE")
    @SequenceGenerator(name = "COURSES_SEQUENCE", sequenceName = "BLOG_COURSE_SEQ", allocationSize = 1)
    @Column(name = "CO_ID", precision = 19)
    private Long id;

    @Column(name = "CO_NAME", length = 100)
    private String name;

    // @ManyToMany(mappedBy = "courses")
    // Set<Author> authors;

    @OneToMany(mappedBy = "course", orphanRemoval = true)
    Set<Subscription> subscriptions;
}
