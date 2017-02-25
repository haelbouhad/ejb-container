package fr.isima.ejb.container;

import java.lang.reflect.Method;

public class Transaction {
	
	private Object bean;
	private Method method;

	public Transaction(Object bean, Method method) {
		this.bean   = bean;
		this.method = method;
	}
	
	public Object getBean() {
		return bean;
	}
	
	public Method getMethod() {
		return method;
	}

}
