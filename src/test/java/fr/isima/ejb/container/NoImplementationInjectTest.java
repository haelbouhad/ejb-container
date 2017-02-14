package fr.isima.ejb.container;

import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.NotImplementedInterface;

public class NoImplementationInjectTest {

	@Inject 
	private NotImplementedInterface service;
	
	@Test(expected = NoExistingImplementation.class)
	public void test() throws NoExistingImplementation {
		Container.inject(this);
	}

}
