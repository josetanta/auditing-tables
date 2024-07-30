package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.persistence.dto.AuthorDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import edu.systemia.auditing_entities.infrastructure.persistence.utils.CycleAvoidingMappingContext;
import org.mapstruct.*;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

	@Override
	@Mappings({ 
		@Mapping(target = "active", expression = "java(dto.active() ? \"Y\" : \"N\")")
	})
	Author mapToModel(AuthorDTO dto, @Context CycleAvoidingMappingContext context);

	@Override
	@Mappings({ 
		@Mapping(target = "active", expression = "java(domain.getActive().equalsIgnoreCase(\"Y\"))")
	})
	AuthorDTO mapToDto(Author domain, @Context CycleAvoidingMappingContext context);

}
