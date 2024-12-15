package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.dto.AuthorDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(
	componentModel = MappingConstants.ComponentModel.SPRING,
	uses = {
		SubscriptionMapper.class,
		NoteMapper.class
	}
)
public interface AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

	@Override
	@Mappings({
		@Mapping(target = "activateAt", ignore = true),
		@Mapping(target = "deactivateAt", ignore = true)
	})
	Author toModel(AuthorDTO dto, @Context CycleAvoidingMappingContext context);

	@Override
	AuthorDTO toDto(Author author, @Context CycleAvoidingMappingContext context);

}
