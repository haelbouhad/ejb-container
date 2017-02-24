package fr.isima.ejb.container;
import static org.junit.Assert.*;

import java.lang.reflect.Proxy;

import org.junit.Before;
import org.junit.Test;

import fr.isima.ejb.container.Container;
import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.exceptions.MultipleExistingImplementation;
import fr.isima.ejb.container.exceptions.NoExistingImplementation;
import fr.isima.ejb.container.mocks.SingletonService;
import fr.isima.ejb.container.mocks.SingletonServiceImpl;

public class SingletonTest {
	
	@Inject
	SingletonService service1;
	
	@Inject
	SingletonService service2;

	@Before
	public void init() throws NoExistingImplementation, MultipleExistingImplementation{
		Container.inject(this);
	}
	
    @Test
    public void test() {
        assertTrue(service1 instanceof Proxy);
        assertTrue(service2 instanceof Proxy);

        // version without EJBHandler
        assertTrue(service1.equals(service2));
        // version with EJBHandler
    }


}
