package edu.systemia.auditing_entities.infrastructure.persistence.entity.metalmodel;

import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Author.class)
public class Author_ {
	public static volatile SingularAttribute<Author, Long> id;
	public static volatile SingularAttribute<Author, String> lastname;
	public static volatile ListAttribute<Author, Subscription> subscriptions;
}
