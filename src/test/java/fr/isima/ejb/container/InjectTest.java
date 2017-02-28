package fr.isima.ejb.container;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.classes.CascadedInterfaceImplementation;
import fr.isima.ejb.container.mocks.classes.IServiceImpl;
import fr.isima.ejb.container.mocks.interfaces.CascadedInterface;
import fr.isima.ejb.container.mocks.interfaces.IService;

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
	private CascadedInterface cascade;

	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);
	}
	
	@Test
	public void simpleInjectTest() {
		assertNotNull(service);
		assertTrue(service instanceof Proxy);
		assertTrue(Container.getBean(service) instanceof IServiceImpl);
	}
	
	@Test
	public void cascadeInjectTest(){
		assertNotNull(cascade);
		assertTrue(cascade instanceof Proxy);
		assertTrue(Container.getBean(cascade) instanceof CascadedInterfaceImplementation);
		
		//* 
		assertNotNull(((CascadedInterfaceImplementation)Container.getBean(cascade)).service);
		assertTrue(
				Container.getBean(((CascadedInterfaceImplementation)Container.getBean(cascade)).service)
				instanceof IServiceImpl
		);
		//*/
	}

}
