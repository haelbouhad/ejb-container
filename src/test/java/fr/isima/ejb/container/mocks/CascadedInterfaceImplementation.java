package fr.isima.ejb.container.mocks;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.Stateless;

@Stateless
public class CascadedInterfaceImplementation implements CascadedInterface{
	@Inject
	public IService service;
	
}
