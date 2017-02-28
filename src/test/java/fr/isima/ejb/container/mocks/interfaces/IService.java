package fr.isima.ejb.container.mocks.interfaces;

public interface IService {
	
	public void method();
	
	public void method2();
	
	public void doRequiredTransaction();
	
	public void doRequiresNewTransaction();
	
	public void doNeverTransaction();
}
