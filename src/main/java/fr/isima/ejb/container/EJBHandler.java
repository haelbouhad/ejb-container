package fr.isima.ejb.container;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.isima.ejb.container.annotations.Log;
import fr.isima.ejb.container.annotations.TransactionAttribute;
import fr.isima.ejb.container.interceptors.Interceptor;
import fr.isima.ejb.container.interceptors.Invocation;
import fr.isima.ejb.container.interceptors.LogInterceptor;

public class EJBHandler implements InvocationHandler {

	private Class<?> beanClass;
	private Object bean;
	private List<Interceptor> interceptors;	
	private Map<Method, List<Interceptor>> methodInterceptors;
	private BeanManager beanManager;
	
	
	public EJBHandler(Class<?> beanClass){
		this.beanClass = beanClass;
		beanManager = BeanManager.getInstance();
		bean = beanManager.getBeanOfClass(beanClass);
		interceptors = new ArrayList<Interceptor>();
		methodInterceptors = new HashMap<Method, List<Interceptor>>();
	}
	
	public EJBHandler(Object bean, List<Interceptor> interceptors) {
		this.bean = bean;
		this.interceptors = interceptors;
	}

	public static Object newInstance(Class<?> beanClass, Class<?>[] interfaces){
		return Proxy.newProxyInstance(beanClass.getClassLoader(), interfaces, new EJBHandler(beanClass) );
	}
	
	public static Object getBeanOf(Object proxy){
		return ((EJBHandler)Proxy.getInvocationHandler(proxy)).getBean();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		
		Object result = null;
		
		
		// Call of equals method
		if(method.getName().equals("equals")){
			result = ( bean == getBeanOf(args[0]) );
		}else if(method.getName().equals("toString")){
			result = beanClass.getName();
		}else{
			method = beanClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
			TransactionAttribute.Type type = getTransactionType(method);
			TransactionManager.start(bean, method, type);
				
				assignInterceptors(method);
				Invocation invocation = new Invocation(bean, methodInterceptors.get(method), method, args);
				result = invocation.nextInterceptor();
				
			TransactionManager.stop(bean, method, type);
		}
		
		return result;
	}
	
	private void assignInterceptors(Method method) {
		if(method.getAnnotation(Log.class) != null)
			methodInterceptors.put(method, Arrays.asList(new LogInterceptor()));
		
	}

	private TransactionAttribute.Type getTransactionType(Method method) {
		
		TransactionAttribute.Type result = TransactionAttribute.Type.NEVER;
		
		TransactionAttribute ta = method.getAnnotation(TransactionAttribute.class);
		
		if(ta != null)
			result = ta.value();
			
		return result;
	}

	public Class<?> getBeanClass() {
		return beanClass;
	}
	
	public Object getBean() {
		return bean;
	}

}
