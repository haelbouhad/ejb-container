package fr.isima.ejb.container.interceptors;

import java.lang.reflect.Method;

import fr.isima.ejb.container.TransactionManager;
import fr.isima.ejb.container.annotations.TransactionAttribute;
import fr.isima.ejb.container.exceptions.EJBException;

public class TransactionInterceptor implements Interceptor {

	@Override
	public Object invoke(Invocation invocation) throws Exception {
		
		
			Object bean = invocation.getBean();
            Method method = invocation.getMethod();
            Object[] args = invocation.getArgs();
            
			TransactionAttribute.Type type = getTransactionType(method);
			TransactionManager.start(bean, method, type);
				method.invoke(bean, args);
			TransactionManager.stop(invocation.getBean(), invocation.getMethod(), type);
			

		
		return null;
	}
	
	private TransactionAttribute.Type getTransactionType(Method method) {
		
		TransactionAttribute.Type result = TransactionAttribute.Type.NEVER;
		
		TransactionAttribute ta = method.getAnnotation(TransactionAttribute.class);
		
		if(ta != null)
			result = ta.value();
			
		return result;
	}


}
