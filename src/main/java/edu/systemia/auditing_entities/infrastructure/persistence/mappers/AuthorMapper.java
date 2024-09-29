package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.dto.AuthorDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

	@Override
	@Mappings({
		@Mapping(target = "active", expression = "java(dto.active() ? \"Y\" : \"N\")")
	})
	Author toModel(AuthorDTO dto, @Context CycleAvoidingMappingContext context);

	@Override
	@Mappings({
		@Mapping(target = "active", expression = "java(author.getActive().equalsIgnoreCase(\"Y\"))")
	})
	AuthorDTO toDto(Author author, @Context CycleAvoidingMappingContext context);

}
