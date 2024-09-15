package edu.systemia.auditing_entities.infrastructure.persistence.mappers;

import edu.systemia.auditing_entities.infrastructure.utils.CycleAvoidingMappingContext;
import org.mapstruct.Context;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Mapper between Model and DTO
 *
 * @param <M> Model or Entity
 * @param <D> DTO
 */
public interface AbstractMapper<M, D> {

	M toModel(D dto, @Context CycleAvoidingMappingContext context);

	List<M> toModel(List<D> dtos, @Context CycleAvoidingMappingContext context);

	D toDto(M domain, @Context CycleAvoidingMappingContext context);

	List<D> toDto(List<M> domains, @Context CycleAvoidingMappingContext context);

	default Page<D> toDto(Page<M> domains, @Context CycleAvoidingMappingContext context) {
		return domains.map(domain -> toDto(domain, context));
	}

}
