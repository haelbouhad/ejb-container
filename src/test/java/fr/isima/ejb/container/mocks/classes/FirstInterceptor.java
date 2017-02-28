package fr.isima.ejb.container.mocks.classes;

import fr.isima.ejb.container.annotations.PostConstruct;
import fr.isima.ejb.container.annotations.PreDestroy;

public class FirstInterceptor {
	
	public static Boolean postConstructWasCalled = false;
	
	public static Boolean preDestructWasCalled = false;
	
	@PostConstruct
	public void initialisation(){
		postConstructWasCalled = true;
	}
	
	@PreDestroy
	public void destruction(){
		preDestructWasCalled = true;
	}

}
