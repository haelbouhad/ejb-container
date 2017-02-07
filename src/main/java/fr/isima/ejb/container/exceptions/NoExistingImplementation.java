package fr.isima.ejb.container.exceptions;

public class NoExistingImplementation extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NoExistingImplementation(String interfaceName) {
		super("No implementation found for interface : " + interfaceName);
	}
	 
	
	

}
