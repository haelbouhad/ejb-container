package fr.isima.ejb.container;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.NotImplementedInterface;

public class ClassLoaderTest {
	
	@Inject
	private NotImplementedInterface service;
	
	public void init(){
		
	}

	@Test
	public void test() throws NoExistingImplementation {
		Container.inject(this);
		assertNull(service);
	}

}
