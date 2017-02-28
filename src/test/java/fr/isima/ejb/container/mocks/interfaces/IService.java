package fr.isima.ejb.container.mocks.interfaces;



public interface IService {
	
	public void FirstLoggedMethod();	
	public void SecondLoggedMethod();
		
	public void notLoggedMethod();
		
	
	public void loggedTransactionMethod();
	
	public void doRequiredTransaction();
	
	public void doRequiresNewTransaction();
	
	public void doNeverTransaction();
}
