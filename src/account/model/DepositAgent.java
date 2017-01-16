package account.model;

import javax.swing.SwingUtilities;


/**
 * @author Leonel
 *
 */
public class DepositAgent extends AbstractModel implements Runnable, Agent{
	BankAccount account;
	private String id;
	private double amountIn;
	private int ops;
	private double transferred;
	private int opsCompleted;
	private volatile boolean activeAgent;
	private boolean pauseAgent;
	private AgentStatus status;
	Object lock;
	
	/**
	 * @param account
	 */
	public DepositAgent(BankAccount account) {
		this.account = account;
		this.activeAgent= true;
		this.pauseAgent = true;
		this.amountIn = 0;
		this.ops = 0;
		this.transferred = 0;
		this.opsCompleted = 0;
		lock = new Object();
		this.status = AgentStatus.running;
	}
	
	@Override
	public void pauseAgent() {
		synchronized (lock){
			pauseAgent = true;
			setState(AgentStatus.stopped);
		}
		
	}

	@Override
	public void resumeAgent() {
		synchronized(lock){
			pauseAgent = false;
			setState(AgentStatus.running);
			lock.notify();
		}
		
	}
	
	public void run() {
		while(activeAgent){
			synchronized(lock){
				while(pauseAgent){
					try{
						lock.wait();
					} catch(InterruptedException e){
						System.out.println("Thread " + Thread.currentThread().getName());
					}
				}
			}
			
			
				this.account.deposit(amountIn);
				System.out.println("running agent");
				this.transferred += amountIn;
				this.opsCompleted += ops;
				this.setState(AgentStatus.running);
				System.out.println(this.transferred);
				final ModelEvent me = new ModelEvent(this.getState(), amountIn, ops, this.transferred, id, opsCompleted);
				SwingUtilities.invokeLater(
						new Runnable() {
						    public void run() {
						    	notifyChanged(me);
						    }
						});
			
			try {
				Thread.sleep(1000/ops);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void setAgentID(String id) {
		this.id = id;
		
	}

	@Override
	public void setAmountin$(double amountIn) {
		this.amountIn = amountIn;
		
	}
	
	@Override
	public void setOPS(int ops) {
		this.ops = ops;
		
	}
	
	public void setFields(){
		System.out.println("setFields");		
		final ModelEvent me = new ModelEvent(this.status, this.amountIn, this.ops, this.transferred, this.id, this.opsCompleted);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				notifyChanged(me);
			}
		});
		opsCompleted = ops;
		transferred = 0;
		resumeAgent();
	}

	@Override
	public int getOPS() {
		return ops;

		
	}

	@Override
	public AgentStatus getState() {
		return this.status;
		
	}


	@Override
	public void setState(AgentStatus agent) {
		status = agent;
		ModelEvent me = new ModelEvent(agent, amountIn, ops, transferred, id, opsCompleted);
		SwingUtilities.invokeLater(
			new Runnable(){
				public void run(){
					notifyChanged(me);
				}
			});
		
	}


	@Override
	public String getAgentID() {

		return id;
	}


	@Override
	public double getTransferred() {
		return transferred;
		
	}

	@Override
	public int getOPSCompleted() {
		return opsCompleted;
		
		
	}
	@Override
	public BankAccount getAccount() {
		return account;
	}

	public void finishThread(){
		activeAgent = false;
	}

	/**
	 * @param string
	 */
	public void setName(String string) {
		this.account.getName();
		
	}

	@Override
	public double getAmountin() {
		// TODO Auto-generated method stub
		return amountIn;
	}
}
