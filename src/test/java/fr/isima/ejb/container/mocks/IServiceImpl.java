package fr.isima.ejb.container.mocks;


import fr.isima.ejb.container.annotations.Log;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.logging.Logger;

@Stateless
public class IServiceImpl implements IService {

	/** when testing for cascades**/
	/*
	@Inject
	public IService service;
	//*/
	
	@Log
	public void method(){
		StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
		String className =  (stackTraceElements[1].getClassName());
		System.out.println(Logger.getClass(className) + "." + stackTraceElements[1].getMethodName() + "()");
	}
}
