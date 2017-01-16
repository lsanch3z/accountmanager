package account.model;

import java.util.concurrent.locks.ReentrantLock;

import javax.swing.SwingUtilities;


public class WithdrawAgent extends AbstractModel implements Runnable, Agent{
	BankAccount account;
	private String id;
	private double amountIn;
	private int ops;
	private double transferred;
	private int opsCompleted;
	private AgentStatus status;
	private volatile boolean activeAgent;
	private boolean pauseAgent;
	ReentrantLock lock;
	public WithdrawAgent(BankAccount account) {
		this.account = account;
		this.activeAgent= true;
		this.pauseAgent = true;
		lock = new ReentrantLock();
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
			setState(AgentStatus.running);;
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
				try {
					account.autoWithdraw(amountIn, this);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("running agent");
				this.transferred += amountIn;
				this.opsCompleted += ops;
				final ModelEvent me = new ModelEvent(status, amountIn, ops, transferred, id, opsCompleted);
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
		final ModelEvent me = new ModelEvent(AgentStatus.NA, amountIn, ops, transferred, id, opsCompleted);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public  AgentStatus getState() {
		return this.status;
		
	}


	@Override
	public synchronized void setState(AgentStatus agent) {
    	status = agent;
    	final ModelEvent me = new ModelEvent(status, amountIn, ops, transferred, id, opsCompleted);
    	SwingUtilities.invokeLater(
				new Runnable() {
				    public void run() {
				    	notifyChanged(me);
				    }
				});
    		notifyAll();
		}
		


	@Override
	public String getAgentID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public double getTransferred() {
		return transferred;
		
	}

	@Override
	public int getOPSCompleted() {
		return opsCompleted;
		// TODO Auto-generated method stub
		
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

	/* (non-Javadoc)
	 * @see account.model.Agent#getAmountin()
	 */
	@Override
	public double getAmountin() {
		// TODO Auto-generated method stub
		return amountIn;
	}


}