package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.domain.dto.SubscriptionDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Subscription;
import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SubscriptionMapper extends AbstractMapper<Subscription, SubscriptionDTO> {

	@Mappings({
		@Mapping(source = "author.id", target = "authorId"),
		@Mapping(source = "course.id", target = "courseId")
	})
	SubscriptionDTO toDto(Subscription subscription, @Context CycleAvoidingMappingContext context);

	@Mappings({
		@Mapping(source = "authorId", target = "author.id"),
		@Mapping(source = "courseId", target = "course.id")
	})
	Subscription toModel(SubscriptionDTO dto, @Context CycleAvoidingMappingContext context);
}
