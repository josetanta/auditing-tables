package edu.systemia.auditing_entities.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BLOG_SUBSCRIPTION")
@EntityListeners(AuditingEntityListener.class)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBSCRIPTION_SEQUENCE")
    @SequenceGenerator(name = "SUBSCRIPTION_SEQUENCE", sequenceName = "BLOG_SUBSCRIPTION_SEQ", allocationSize = 1)
    @Column(name = "SUB_ID", precision = 19)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AT_ID")
    @ToString.Exclude
    private Author author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CO_ID")
    @ToString.Exclude
    private Course course;

    @Column(name = "CREATE_AT", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;
}
