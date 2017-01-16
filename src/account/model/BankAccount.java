package account.model;

import javax.swing.SwingUtilities;

import account.model.ModelEvent.typeOfCurrency;

/**
 * BankAccount class
 * 
 * @author Leonel
 * 
 */
public class BankAccount extends AbstractModel {
	private int account;
	private double balance;
	private String name;

	public final static double euros = .79;
	public final static double yen = 94.1;

	public typeOfCurrency curr;
	/**
	 * BankAccount constructor
	 * @param account
	 * @param balance
	 * @param name
	 */
	public BankAccount(int account, double balance, String name){
		this.account = account;
		this.balance = balance;
		this.name = name;
		
	}
	
		
	/**
	 * 
	 * @return
	 */
	public BankAccount getBankAccount()
	{
		return this;	
	}
	
	/**
	 * @return
	 */
	public int getAccount() {
		return account;
	}



	/**
	 * @param account
	 */
	public void setAccount(int account) {
		this.account = account;
	}



	/**
	 * @return
	 */
	public double getBalance() {
		double exchange = balance;
		if(typeOfCurrency.dollars == curr){
			exchange = balance;
		}else if(typeOfCurrency.Yen == curr){
			exchange = balance*yen;
		}else if(typeOfCurrency.Euros == curr){
			exchange = balance*euros;
		}
		return exchange;
	}



	/**
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}



	/**
	 * @return
	 */
	public String getName() {
		return name;
	}



	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public void setCurrency(typeOfCurrency currency){
		this.curr = currency;
	}
	
	public typeOfCurrency getCurrency(){
		System.out.println(curr);
		return curr;
		
	}
	
	/**
	 * @param amount
	 */
	public synchronized void deposit(double amount){
		double oldBalance = balance;
		double newBalance = oldBalance + amount;;
		balance = newBalance;
		double exchange = balance;

		
		final ModelEvent me = new ModelEvent(curr, exchange, amount, AgentStatus.running);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				notifyChanged(me);
			}
		});
		notifyAll();
		
	}

	
	/**
	 * @param amount
	 * @throws OverdrawException
	 */
	public synchronized void withdraw(double amount)  throws OverdrawException{
		
		double oldBalance = balance;
		double newBalance = oldBalance-amount;
		if(newBalance < 0)throw (new OverdrawException());
		balance = newBalance;
		
		final ModelEvent me = new ModelEvent(typeOfCurrency.dollars, balance, amount, AgentStatus.NA);
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				notifyChanged(me);
			}
		});
		notifyAll();
	}


	/**
	 * @param amount
	 * @param agent
	 * @throws InterruptedException 
	 */
	public synchronized void autoWithdraw(double amount , Agent agent) throws InterruptedException {
		double oldBalance = balance;
		double newBalance = oldBalance-amount;
		System.out.println("newBalance before while "+newBalance);
		while(newBalance < 0) {
			agent.setState(AgentStatus.blocked);
			wait();
			return;
		}
			if(agent.getState() == AgentStatus.stopped)return;
			
			agent.setState(AgentStatus.running);
		System.out.println("newBalance before while "+newBalance);
		balance = newBalance;
		System.out.println(agent.toString());
		final ModelEvent me = new ModelEvent(typeOfCurrency.dollars, balance, amount, agent.getState());
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				notifyChanged(me);
			}
		});

	} 
}
