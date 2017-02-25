package fr.isima.ejb.container;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.TransactionAttribute;
import fr.isima.ejb.container.exceptions.EJBException;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.IService;

public class TransactionTest {

	@Inject
	private IService service;
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);	
		TransactionHelper.clear();
	}
	
	@Test
	public void requiredWithoutTransactionTest(){
		service.doRequiredTransaction();
		Assert.assertTrue(TransactionHelper.getCounter() == 1);
		Assert.assertTrue(TransactionHelper.getAll().empty());
	}
	
	@Test
	public void requiredWithTransactionTest() throws EJBException{
		TransactionHelper.start(this, null, TransactionAttribute.Type.REQUIRES_NEW);
		Assert.assertTrue(TransactionHelper.getCounter() == 1);
		service.doRequiredTransaction();
		Assert.assertTrue(TransactionHelper.getCounter() == 1);
		Assert.assertTrue(TransactionHelper.getAll().size() == 1);		
	}
	
	@Test
	public void requiresNewTransactionTest() throws EJBException{
		TransactionHelper.start(this, null, TransactionAttribute.Type.REQUIRES_NEW);
		Assert.assertTrue(TransactionHelper.getCounter() == 1);
		service.doRequiresNewTransaction();
		Assert.assertTrue(TransactionHelper.getCounter() == 2);
		Assert.assertTrue(TransactionHelper.getAll().size() == 1);
		Assert.assertTrue(TransactionHelper.getAll().peek().getBean() == this);
	}
	
	@Test
	public void neverWithoutTransactionTest(){
		service.doNeverTransaction();
		Assert.assertTrue(TransactionHelper.getCounter() == 0);
		Assert.assertTrue(TransactionHelper.getAll().empty());
	}
	
	@Test(expected=Throwable.class)
	public void neverWithTransactionTest() throws Throwable {
		TransactionHelper.start(this, null, TransactionAttribute.Type.REQUIRES_NEW);
		Assert.assertTrue(TransactionHelper.getCounter() == 1);
		service.doNeverTransaction();
	}
	
	
	
}
