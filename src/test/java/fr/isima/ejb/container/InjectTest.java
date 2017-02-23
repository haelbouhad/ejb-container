package fr.isima.ejb.container;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.CascadedInterface;
import fr.isima.ejb.container.mocks.CascadedInterfaceImplementation;
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
	private CascadedInterface cascade;

	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);
	}
	
	private Class<?> classOfProxy(Object proxy){
		return ((EJBHandler)Proxy.getInvocationHandler(proxy)).getBeanClass();
	}

	@Test
	public void simpleInjectTest() {
		assertNotNull(service);
		assertTrue(service instanceof Proxy);
		assertEquals(classOfProxy(service), IServiceImpl.class);
	}
	
	@Test
	public void cascadeInjectTest(){
		assertNotNull(cascade);
		assertTrue(cascade instanceof Proxy);
		assertEquals(classOfProxy(cascade), CascadedInterfaceImplementation.class);
		
		//* 
		//assertNotNull(((CascadedInterfaceImplementation)cascade).service);
		//assertTrue(((CascadedInterfaceImplementation)cascade).service instanceof IServiceImpl);
		//*/
	}

}
