package fr.isima.ejb.container;

import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.classes.INoPrefImplService;

/* this test is performed with a service that has multiple implementations
  * but does not one with the @Prefered annotation */
public class MultipleImplementationInjectTest {

	//*
	@Inject 
	private INoPrefImplService service;
	
	@Test(expected = MultipleExistingImplementation.class)
	public void test() throws NoExistingImplementation, MultipleExistingImplementation {
		Container.inject(this);
	}
	//*/

}
