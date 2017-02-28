package fr.isima.ejb.container;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.logging.Logger;
import fr.isima.ejb.container.mocks.interfaces.IService;

public class LogTest {
	
	@Inject
	private IService service;
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);
	}
	
	@Test
	public void test(){
		
		service.method();
		assertTrue(Logger.contains("IServiceImpl.method()"));
		assertFalse(Logger.contains("IServiceImpl.method2()"));
		
		service.method2();
		assertTrue(Logger.contains("IServiceImpl.method2()"));
		
	}
	
	
	
	
}
