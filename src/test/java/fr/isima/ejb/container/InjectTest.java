package fr.isima.ejb.container;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.mocks.IService;
import fr.isima.ejb.container.mocks.IServiceImpl;

public class InjectTest {
	
	@Inject
	private IService service;
	
	@Before
	public void init(){
		Container.inject(this);
	}

	@Test
	public void test() {
		assertNotNull(service);
		assertTrue(service instanceof IServiceImpl);
	}

}
