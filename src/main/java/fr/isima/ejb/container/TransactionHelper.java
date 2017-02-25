package fr.isima.ejb.container;

import java.lang.reflect.Method;
import java.util.Stack;

import javax.management.modelmbean.RequiredModelMBean;

import fr.isima.ejb.container.annotations.TransactionAttribute.Type;
import fr.isima.ejb.container.exceptions.EJBException;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;

public class TransactionHelper {

	private static int counter = 0;
	private static Stack<Transaction> all = new Stack<Transaction>();
	

	public static int getCounter() {
		return counter;
	}

	public static Stack<Transaction> getAll() {
		return all ;
	}

	public static void start(Object bean, Method method, Type type) throws EJBException {
		switch (type) {
			case NEVER :
				if(!all.isEmpty())
					throw new EJBException();
				break;
				
			case REQUIRED :
				if(all.isEmpty()){
					make(bean, method);
				}
				break;
			
			case REQUIRES_NEW:
				make(bean, method);
				break;
		}
	}

	private static void make(Object bean, Method method) {
		all.push(new Transaction(bean, method));
		counter++;
	}

	public static void stop(Object bean, Method method, Type type) {
		switch (type) {
			case NEVER:				
				break;
				
			case REQUIRED:
				if(all.peek().getBean() == bean && all.peek().getMethod() == method)
					all.pop();
				break;
				
			case REQUIRES_NEW:	
				if(all.peek().getBean() == bean && all.peek().getMethod() == method)
					all.pop();
				break;

		}
	}

	public static void clear() {
		all.clear();
		counter = 0;
	}

}
