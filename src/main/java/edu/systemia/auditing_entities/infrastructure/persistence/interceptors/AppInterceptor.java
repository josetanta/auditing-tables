package edu.systemia.auditing_entities.infrastructure.persistence.interceptors;


import edu.systemia.auditing_entities.infrastructure.persistence.entity.Author;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.beans.BeanInfo;
import java.beans.FeatureDescriptor;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class AppInterceptor implements Interceptor {

	@Override
	public boolean onSave(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		log.info("[ON SAVE] entity={} state={} properties={}", entity, Arrays.toString(state), Arrays.toString(propertyNames));
		return Interceptor.super.onSave(entity, id, state, propertyNames, types);
	}

	@Override
	public void onDelete(Object entity, Object id, Object[] state, String[] propertyNames, Type[] types) throws CallbackException {
		log.info("[ON DELETE] entity={} state={} properties={}", entity, Arrays.toString(state), Arrays.toString(propertyNames));
		Interceptor.super.onDelete(entity, id, state, propertyNames, types);
	}

	@Override
	public boolean onFlushDirty(Object entity, Object id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) throws CallbackException {
		log.info("[ON UPDATE] entity={}", entity);
		log.info("[ON UPDATE] id={}", id);
		log.info("[ON UPDATE] currentState={}", Arrays.toString(currentState));
		log.info("[ON UPDATE] previousState={}", Arrays.toString(previousState));
		log.info("[ON UPDATE] properties={}", Arrays.toString(propertyNames));
		log.info("[ON UPDATE] types={}", Arrays.toString(types));

		if (entity instanceof Author author) {
			log.info("[ON UPDATE Author] firstname={}", author.getFirstname());
			try {
				BeanInfo beanInfo = Introspector.getBeanInfo(Author.class);

				var EXCLUDED_PROPERTIES = Set.of(
					"class", "id", "notes", "activateAt", "createdAt", "deactivateAt", "updatedAt", "subscriptions"
				);
				List<String> list = Arrays.stream(beanInfo.getPropertyDescriptors()).map(FeatureDescriptor::getName)
					.filter(attr -> !EXCLUDED_PROPERTIES.contains(attr))
					.toList();
				log.info("getPropertyDescriptors {}", list);

				var ref = new Object() {
					String valueCurrState;
					String valuePrevState;
				};
				// get its index
				List<String> listPropertyNames = List.of(propertyNames);
				list.forEach(attr -> {
					int idxOfPropertyName = listPropertyNames.indexOf(attr);
					ref.valueCurrState = Optional.ofNullable(currentState[idxOfPropertyName]).map(Object::toString).orElse("");
					ref.valuePrevState = Optional.ofNullable(previousState[idxOfPropertyName]).map(Object::toString).orElse("");
					log.info("!!! [VALUES] prev='{}' curr='{}' from '{}' values is equals? {}", ref.valuePrevState, ref.valueCurrState, attr, ObjectUtils.nullSafeEquals(ref.valuePrevState, ref.valueCurrState));
				});
			} catch (SecurityException | IntrospectionException e) {
				log.error("Error {}", e.getMessage());
			}
		}

		return Interceptor.super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}
}
