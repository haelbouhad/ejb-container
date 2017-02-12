package fr.isima.ejb.container;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.mocks.MultipleService;
import fr.isima.ejb.container.mocks.MultipleServiceImpl2;

public class PreferredTest {

	@Inject
	private MultipleService service;
	
	@Before
	public void init(){
		Container.inject(this);
	}
	
	@Test
	public void test() {
		assertTrue(service instanceof MultipleServiceImpl2);
	}

}
