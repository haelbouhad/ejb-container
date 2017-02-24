package fr.isima.ejb.container;

import java.util.Stack;

import fr.isima.ejb.container.annotations.TransactionAttribute.Type;

public class Transaction {

	private static int counter = 0;
	private static Stack<Transaction> all = new Stack<Transaction>();
	

	public static int getCounter() {
		return counter;
	}

	public static Stack<Transaction> getAll() {
		return all ;
	}

	public static void start(Type type) {
		switch (type) {
			case NEVER :
				break;
				
			case REQUIRED :
				if(all.isEmpty()){
					all.push(new Transaction());
					counter++;
				}
				break;
			
			case REQUIRES_NEW:
				break;
		}
	}

	public static void stop(Type type) {
		all.pop();
	}

}
