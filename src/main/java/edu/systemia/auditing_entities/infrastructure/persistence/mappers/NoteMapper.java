package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.dto.NoteDTO;
import edu.systemia.auditing_entities.infrastructure.persistence.entity.Note;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(
	componentModel = MappingConstants.ComponentModel.SPRING
)
public interface NoteMapper extends AbstractMapper<Note, NoteDTO> {

}
