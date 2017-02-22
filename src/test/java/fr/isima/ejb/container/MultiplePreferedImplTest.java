package fr.isima.ejb.container;

import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.IMultiplePrefService;

public class MultiplePreferedImplTest {

	@Inject 
	private IMultiplePrefService service;
	
	@Test(expected = MultipleExistingImplementation.class)
	public void test() throws NoExistingImplementation, MultipleExistingImplementation {
		Container.inject(this);
	}
}