package fr.isima.ejb.container.interceptors;

import java.lang.reflect.Method;
import java.util.List;

import fr.isima.ejb.container.exceptions.EJBException;

public class Invocation {
	
	private Object bean;
	
	private List<Interceptor> interceptors;
	
	private Method method;
	
	private Object[] args;
	
	private int index;

	public Invocation(Object bean, List<Interceptor> interceptors, Method method, Object[] args) {
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
	
	public Object nextInterceptor() throws Exception{
		Object result = null;
		
        if(interceptors != null && index < interceptors.size()){
            result = interceptors.get(index++).invoke(this);
        }
        
        return result;

	}
	
	

}
