package edu.systemia.auditing_entities.infrastructure.persistence.interceptors;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.CallbackException;
import org.hibernate.Interceptor;
import org.hibernate.type.Type;
import org.springframework.stereotype.Component;

import java.util.Arrays;

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

}
