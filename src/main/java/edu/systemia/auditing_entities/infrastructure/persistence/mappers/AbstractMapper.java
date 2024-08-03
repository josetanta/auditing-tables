package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import org.mapstruct.Context;
import org.springframework.data.domain.Page;

import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;

import java.util.List;

/**
 * Mapper between Model and DTO
 *
 * @param <M> Model or Entity
 * @param <D> DTO
 */
public interface AbstractMapper<M, D> {

	M mapToModel(D dto, @Context CycleAvoidingMappingContext context);

	List<M> mapToModel(List<D> dtos, @Context CycleAvoidingMappingContext context);

	D mapToDto(M domain, @Context CycleAvoidingMappingContext context);

	List<D> mapToDto(List<M> domains, @Context CycleAvoidingMappingContext context);

	default Page<D> mapToDto(Page<M> domains, @Context CycleAvoidingMappingContext context) {
		return domains.map(domain -> this.mapToDto(domain, context));
	}

}
