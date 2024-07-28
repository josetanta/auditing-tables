package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.persistence.dto.SubscriptionDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mappings({ @Mapping(source = "author.id", target = "authorId"),
	    @Mapping(source = "course.id", target = "courseId") })
    SubscriptionDTO toSubscriptionDTO(Subscription subscription);

    @Mappings({ @Mapping(source = "authorId", target = "author.id"),
	    @Mapping(source = "courseId", target = "course.id") })
    Subscription toSubscription(SubscriptionDTO dto);
}
