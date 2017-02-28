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
		Logger.reset();
	}
	
	@Test
	public void basicLogTest(){

		
		service.FirstLoggedMethod();
		assertTrue(Logger.contains("IServiceImpl.FirstLoggedMethod()"));
		assertTrue(Logger.size() == 1);		
	}
	
	
	@Test
	public void NonLogTest(){
		
		service.notLoggedMethod();		
		assertTrue(Logger.size() == 0);	
		
	}
	
	@Test
	public void cascadedLogTest(){

		
		service.SecondLoggedMethod();
		
		assertTrue(Logger.contains("IServiceImpl.SecondLoggedMethod()"));
		assertTrue(Logger.size() == 2);	
		assertTrue(Logger.contains("IServiceImpl.FirstLoggedMethod()"));
		
	}

	
	
	
	
}
