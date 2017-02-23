package fr.isima.ejb.container;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.logging.Logger;
import fr.isima.ejb.container.mocks.IService;

public class LogTest {
	
	@Inject
	private IService service;
	
	@Before
	public void init() throws NoExistingImplementation{
		Container.inject(this);
	}
	
	@Test
	public void test(){
		service.method();
		assertTrue(Logger.contains("IService.method()"));
	}
	
	
	
	
}
