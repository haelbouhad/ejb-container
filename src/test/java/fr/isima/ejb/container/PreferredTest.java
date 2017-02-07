package fr.isima.ejb.container;

import static org.junit.Assert.*;

import org.junit.Test;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.mocks.MultipleService;

public class PreferredTest {

	@Inject
	MultipleService service;
	
	public void init(){
		Container.inject(this);
	}
	
	@Test
	public void test() {
		System.out.println(service.getClass().getName());
	}

}
