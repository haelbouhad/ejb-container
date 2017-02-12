package fr.isima.ejb.container;

import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.mocks.INoPrefImplService;
import fr.isima.ejb.container.mocks.MultipleService;

/* this test is performed with a service that has multiple implementations
  * but does not one with the @Prefered annotation */
public class MultipleImplementationInjectTest {

	@Inject 
	private INoPrefImplService service;
	
	@Test(expected = MultipleExistingImplementation.class)
	public void test() {
		Container.inject(this);
	}

}
