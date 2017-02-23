package fr.isima.ejb.container.interceptors;

import java.lang.reflect.Method;

import fr.isima.ejb.container.logging.Logger;

public class LogInterceptor implements  Interceptor {

	@Override
	public Object invoke(Invocation invocation) {
		
		Logger.log(invocation.getClass().getName() + "." + invocation.getMethod().getName());
		
		return invocation.nextInterceptor();
		
	}
    
    
}
