package fr.isima.ejb.container;

import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.classes.IServiceImpl;
import fr.isima.ejb.container.mocks.classes.MultipleServiceImpl2;
import fr.isima.ejb.container.mocks.interfaces.MultipleService;

public class PreferredTest {

	@Inject
	private MultipleService service;
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);
	}
	
	@Test
	public void test() {
		assertNotNull(service);
		assertTrue(service instanceof Proxy);
		System.out.println(Container.getBean(service).getClass());
		assertTrue(Container.getBean(service) instanceof MultipleServiceImpl2);
	}

}
