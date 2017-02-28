package fr.isima.ejb.container;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.TransactionAttribute;
import fr.isima.ejb.container.exceptions.EJBException;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.logging.Logger;
import fr.isima.ejb.container.mocks.interfaces.IService;

public class TransactionTest {

	@Inject
	private IService service;
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);	
		TransactionManager.clear();
	}
	
	@Test
	public void requiredWithoutTransactionTest(){
		service.doRequiredTransaction();
		Assert.assertTrue(TransactionManager.getCounter() == 1);
		Assert.assertTrue(TransactionManager.getAll().empty());
	}
	
	@Test
	public void requiredWithTransactionTest() throws EJBException{
		TransactionManager.start(this, null, TransactionAttribute.Type.REQUIRES_NEW);
		Assert.assertTrue(TransactionManager.getCounter() == 1);
		service.doRequiredTransaction();
		Assert.assertTrue(TransactionManager.getCounter() == 1);
		Assert.assertTrue(TransactionManager.getAll().size() == 1);		
	}
	
	@Test
	public void requiresNewTransactionTest() throws EJBException{
		TransactionManager.start(this, null, TransactionAttribute.Type.REQUIRES_NEW);
		Assert.assertTrue(TransactionManager.getCounter() == 1);
		service.doRequiresNewTransaction();
		Assert.assertTrue(TransactionManager.getCounter() == 2);
		Assert.assertTrue(TransactionManager.getAll().size() == 1);
		Assert.assertTrue(TransactionManager.getAll().peek().getBean() == this);
	}
	
	@Test
	public void neverWithoutTransactionTest(){
		service.doNeverTransaction();
		Assert.assertTrue(TransactionManager.getCounter() == 0);
		Assert.assertTrue(TransactionManager.getAll().empty());
	}
	
	@Test(expected=Throwable.class)
	public void neverWithTransactionTest() throws Throwable {
		TransactionManager.start(this, null, TransactionAttribute.Type.REQUIRES_NEW);
		Assert.assertTrue(TransactionManager.getCounter() == 1);
		service.doNeverTransaction();
	}
	
	@Test
	public void annotationsLogAndTransaction(){
		
		
		service.loggedTransactionMethod();
		Assert.assertTrue(TransactionManager.getCounter() == 1);
		Assert.assertTrue(TransactionManager.getAll().empty());
		assertTrue(Logger.size() == 1);
		assertTrue(Logger.contains("IServiceImpl.loggedTransactionMethod()"));
			
	}
	
	
	
}
