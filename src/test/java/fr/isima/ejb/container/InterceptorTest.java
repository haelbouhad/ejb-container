package fr.isima.ejb.container;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.classes.InterceptedServiceImpl;
import fr.isima.ejb.container.mocks.interfaces.InterceptedService;


public class InterceptorTest {
	
	@Inject
	private InterceptedService service; 
	
	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);
	}

	@Test
	public void test() {
		Assert.assertTrue(Container.getBean(service) instanceof InterceptedServiceImpl);
	}

}
