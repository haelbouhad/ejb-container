package fr.isima.ejb.container.mocks;


import fr.isima.ejb.container.annotations.Log;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.logging.Logger;

@Stateless
public class IServiceImpl implements IService {

	
	@Log
	public void method(){
	}

}
