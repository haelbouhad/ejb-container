package fr.isima.ejb.container.mocks;


import fr.isima.ejb.container.annotations.Log;
import fr.isima.ejb.container.annotations.Stateless;
import fr.isima.ejb.container.annotations.TransactionAttribute;
import fr.isima.ejb.container.annotations.TransactionAttribute.Type;
import fr.isima.ejb.container.logging.Logger;

@Stateless
public class IServiceImpl implements IService {

	
	@Log
	public void method(){
	}
	
	@Log
	public void method2(){
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
	public void doNeverTrnasaction() {
		// TODO Auto-generated method stub
		
	}

}
