package account.model;

public class OverdrawException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String getOverdrawException(double balance, double amount){
		return "Insufficient funds: amount to withdraw is " + amount + ", it is greater than available funds: " + balance;
		
		
	}
}
