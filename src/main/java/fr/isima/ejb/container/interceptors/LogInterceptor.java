package fr.isima.ejb.container.interceptors;

import java.lang.reflect.Method;

import fr.isima.ejb.container.exceptions.EJBException;
import fr.isima.ejb.container.logging.Logger;

public class LogInterceptor implements  Interceptor {

	@Override
	public Object invoke(Invocation invocation) throws Exception {
		
		Logger.log(invocation.getBean().getClass().getSimpleName() + "." + invocation.getMethod().getName()+"()");
		//System.out.println(invocation.getBean().getClass().getSimpleName() + "." + invocation.getMethod().getName()+"()");
		
		return invocation.nextInterceptor();
		
	}
    
    
}
