package fr.isima.ejb.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class BeanManager {
	
	private static BeanManager instance = null;

	private Map<Class<?>, List<Object>> statelessClassToBeans;
	
	private Map<Class<?>, Object> singletonClassToBean;
	
	public static BeanManager getInstance() {
		if(instance ==null)
			instance = new BeanManager();
		return instance;
	}
	
	public BeanManager() {
		statelessClassToBeans = new HashMap<Class<?>, List<Object>>();
		singletonClassToBean = new HashMap<Class<?>, Object>();
	}
	
	public void addStatelessClass(Class<?> ejbClass) {
		statelessClassToBeans.put(ejbClass, new ArrayList<Object>());
	}
	
	public void addSingletonClass(Class<?> ejbClass) {
		singletonClassToBean.put(ejbClass, null);
	}

	public Object getBeanOfClass(Class<?> serviceClass) {
		Object bean = null;
		if(statelessClassToBeans.containsKey(serviceClass)){
			bean = makeStatelessBean(serviceClass);	
			
		}else if(singletonClassToBean.containsKey(serviceClass)){
			if(singletonClassToBean.get(serviceClass) == null)
				singletonClassToBean.put(serviceClass, makeSingletonBean(serviceClass));
			bean = singletonClassToBean.get(serviceClass);
		}
		Container.invokePostConstructOf(bean, serviceClass);
		return bean;
	}

	


	private Object makeStatelessBean(Class<?> serviceClass) {
		Object bean = null;
		try {
			bean = serviceClass.newInstance();
		} catch ( IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	private Object makeSingletonBean(Class<?> serviceClass) {
		Object bean = null;
		
		try {
			bean = serviceClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		return bean;
	}

	public void addAllStatelessClasses(Set<Class<?>> statelessClasses) {
		for(Class<?> ejbClass : statelessClasses)
			addStatelessClass(ejbClass);
		
	}

	public void addAllSingletonClasses(Set<Class<?>> singletonClasses) {
		for(Class<?> ejbClass : singletonClasses)
			addSingletonClass(ejbClass);
		
	}

	
	
	

}
