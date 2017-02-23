package fr.isima.ejb.container.interceptors;

import java.lang.reflect.Method;

public class Invocation {
	
	private Object bean;
	
	private Interceptor[] interceptors;
	
	private Method method;
	
	private Object[] args;
	
	private int index;

	public Invocation(Object bean, Interceptor[] interceptors, Method method, Object[] args) {
		super();
		this.bean = bean;
		this.interceptors = interceptors;
		this.method = method;
		this.args = args;
	}
	
	public Object getBean() {
		return bean;
	}
	
	public Method getMethod() {
		return method;
	}
	
	public Object[] getArgs() {
		return args;
	}
	
	public Object nextInterceptor(){
		return interceptors[index++].invoke(this);
	}
	
	

}
