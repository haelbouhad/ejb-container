package fr.isima.ejb.container;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.IService;

public class TransactionTest {

	@Inject
	private IService service;
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);	
	}
	
	@Test
	public void requiredWithoutTransactionTest(){
		service.doRequiredTransaction();
		Assert.assertTrue(Transaction.getCounter() == 1);
		Assert.assertTrue(Transaction.getAll().empty());
	}
	
	/*
	public void requiredWithTransactionTest(){
		service.doRequiredTransaction();
		Assert.assertTrue(Transaction.getCounter() == 1);
		Assert.assertTrue(Transaction.getAll().size() == 1);
		
	}
	*/
	
	
	
	
	
}
