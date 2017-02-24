package fr.isima.ejb.container;

import org.junit.Before;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.IService;

public class TransactionTest {

	@Inject
	private IService serivce;
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);	
	}
	
	
	
	
}
