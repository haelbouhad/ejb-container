package fr.isima.ejb.container.mocks.classes;


import fr.isima.ejb.container.annotations.Log;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.annotations.TransactionAttribute;
import fr.isima.ejb.container.annotations.TransactionAttribute.Type;
import fr.isima.ejb.container.logging.Logger;
import fr.isima.ejb.container.mocks.interfaces.IService;

@Stateless
public class IServiceImpl implements IService {

	
	@Log
	public void FirstLoggedMethod(){
	}
	
	@Log
	public void SecondLoggedMethod(){
		FirstLoggedMethod();
	}
	
	public void notLoggedMethod(){		
	}

	@TransactionAttribute(Type.REQUIRED)
	public void doRequiredTransaction() {
		// TODO Auto-generated method stub
		
	}

	@TransactionAttribute(Type.REQUIRES_NEW)
	public void doRequiresNewTransaction() {
		// TODO Auto-generated method stub
		
	}

	@TransactionAttribute(Type.NEVER)
	public void doNeverTransaction() {
		// TODO Auto-generated method stub
		
	}

}
