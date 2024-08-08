package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.dto.SubscriptionDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper extends AbstractMapper<Subscription, SubscriptionDTO> {

	// @Mappings({ @Mapping(source = "author.id", target = "authorId"),
	// @Mapping(source = "course.id", target = "courseId") })
	// SubscriptionDTO toSubscriptionDTO(Subscription subscription);
	//
	// @Mappings({ @Mapping(source = "authorId", target = "author.id"),
	// @Mapping(source = "courseId", target = "course.id") })
	// Subscription toSubscription(SubscriptionDTO dto);
}
