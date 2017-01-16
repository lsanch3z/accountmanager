package account.model;


public class ModelEvent{
	public enum typeOfCurrency {
		dollars, Yen, Euros
	}
	
	
	private double balance;
	private double amount;
	private typeOfCurrency curr;
	public AgentStatus status;
	
	/**
	 * @param curr
	 * @param balance
	 * @param amount
	 * @param status
	 */
	public ModelEvent(typeOfCurrency curr, double balance, double amount, AgentStatus status) {
		this.curr = curr;
		this.balance = balance;
		this.amount = amount;
		this.status = status;
	}
	
	/**
	 * @return
	 */
	public typeOfCurrency getCurr() {
		return curr;
	}

	

	/**
	 * @return
	 */
	public double getBalance() {
		return balance;
	}
	/**
	 * @return
	 */
	public AgentStatus getAgStatus(){return status;}
	/**
	 * @return
	 */
	public double getAmount() {
		return amount;
	}
	
	/*model event for agents*/
	
	public enum EventKind {
		BalanceUpdate, AgentStatusUpdate, AmountTransferredUpdate
	}
	double amountIn;
	int operationsPer;
	double amountTransfered;
	String id;
	int opsCompleted;
	private EventKind kind;
	/**
	 * @param status
	 * @param amountIn
	 * @param operationsPer
	 * @param amountTransfered
	 * @param id
	 * @param opsCompleted
	 */
	public ModelEvent(AgentStatus status, double amountIn, int operationsPer, double amountTransfered, String id, int opsCompleted){
		this.amountIn = amountIn;
		this.operationsPer = operationsPer;
		this.amountTransfered = amountTransfered;
		this.id = id;
		this.opsCompleted = opsCompleted;
		this.status=status;
	}
	/**
	 * @return
	 */
	public int getopsCompleted(){
		return opsCompleted;
	}
	
	/**
	 * @return
	 */
	public double getAmountIn() {
		return amountIn;
	}
	/**
	 * @return
	 */
	public AgentStatus Astatus() {
		return status;
	}
	/**
	 * @return
	 */
	public int getOps() {
		return operationsPer;
	}
	/**
	 * @return
	 */
	public double getTransfered() {
		return amountTransfered;
	}
	/**
	 * @return
	 */
	public String getID() {
		return id;
	}
	
	
	
	
}
