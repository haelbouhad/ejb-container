package fr.isima.ejb.container;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.mocks.IService;
import fr.isima.ejb.container.mocks.IServiceImpl;

/* 
 * If @Inject and no implementations found
 * 	return error
 * If @Inject and one implementation 
 * 	return impl
 * If @Inject and multiple implementation
 * 	If @Prefered
 * 		return prefered implementation
 * 	else
 * 		return error
 */

public class InjectTest {
	
	@Inject
	private IService service;
	
	@Inject
	
	@Before
	public void init(){
		Container.inject(this);
	}

	@Test
	public void test() {
		assertNotNull(service);
		assertTrue(service instanceof IServiceImpl);
		// Cascade test
		/*
		assertNotNull(service.service);
		assertTrue(service.service instanceof IServiceImpl);
		//*/
	}

}
