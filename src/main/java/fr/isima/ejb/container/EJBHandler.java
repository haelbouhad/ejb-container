package fr.isima.ejb.container;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import fr.isima.ejb.container.interceptors.Interceptor;
import fr.isima.ejb.container.interceptors.Invocation;

public class EJBHandler implements InvocationHandler {

	private Class<?> beanClass;
	private Object bean;
	private Interceptor[] interceptors;
	private BeanManager beanManager;
	
	
	public EJBHandler(Class<?> beanClass){
		this.beanClass = beanClass;
		beanManager = BeanManager.getInstance();
	}
	
	public EJBHandler(Object bean, Interceptor[] interceptors) {
		this.bean = bean;
		this.interceptors = interceptors;
	}

	public static Object newInstance(Class<?> beanClass, Class<?>[] interfaces){
		return Proxy.newProxyInstance(beanClass.getClassLoader(), interfaces, new EJBHandler(beanClass) );
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/*Invocation invocation = new Invocation(bean, interceptors, method, args);
		return invocation.nextInterceptor();*/
		
		Object result = null;
		bean = beanManager.getBeanOfClass(beanClass);
		
		// Call of equals method
		if(method.getName().equals("equals")){
			result = ( bean == ((Iproxy)args[0]).getBean() );
		}else if(method.getName().equals("getBean")){
			result = bean;
		}
		
		return result;
	}
	
	public Class<?> getBeanClass() {
		return beanClass;
	}

}
