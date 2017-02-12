package fr.isima.ejb.container.exceptions;

public class MultipleExistingImplementation extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MultipleExistingImplementation(String interfaceName) {
		super("Multiple implementations found for interface : " + interfaceName + " with no prefered found");
	}
	 
	
	

}
