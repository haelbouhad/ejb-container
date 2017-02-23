package fr.isima.ejb.container;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import fr.isima.ejb.container.interceptors.Interceptor;
import fr.isima.ejb.container.interceptors.Invocation;

public class EJBHandler implements InvocationHandler {

	private Object bean;
	private Interceptor[] interceptors;
	
	
	
	public EJBHandler(Object bean, Interceptor[] interceptors) {
		this.bean = bean;
		this.interceptors = interceptors;
	}


	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Invocation invocation = new Invocation(bean, interceptors, method, args);
		return invocation.nextInterceptor();
	}

}
