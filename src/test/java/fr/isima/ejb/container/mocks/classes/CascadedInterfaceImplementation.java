package fr.isima.ejb.container.mocks.classes;

import fr.isima.ejb.container.annotations.Inject;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.mocks.interfaces.CascadedInterface;
import fr.isima.ejb.container.mocks.interfaces.IService;

@Stateless
public class CascadedInterfaceImplementation implements CascadedInterface{
	@Inject
	public IService service;
	
}
