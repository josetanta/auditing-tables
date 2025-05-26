package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.dto.AuthorDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
	componentModel = MappingConstants.ComponentModel.SPRING,
	uses = {
		SubscriptionMapper.class,
		NoteMapper.class
	}
)
public interface AuthorMapper extends AbstractMapper<Author, AuthorDTO> {

}
