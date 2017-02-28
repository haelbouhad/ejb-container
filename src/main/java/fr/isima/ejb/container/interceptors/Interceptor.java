package fr.isima.ejb.container.interceptors;

import fr.isima.ejb.container.exceptions.EJBException;

public interface Interceptor {
	
	public Object invoke(Invocation invocation) throws Exception ;

}
