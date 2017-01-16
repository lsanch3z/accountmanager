package account.model;

public interface Agent extends Model{
	
	public void setAgentID(String id);
	public void setAmountin$(double amountIn$);
	public void setOPS(int ops);
	public void setState(AgentStatus agent);


	public void setFields();
	
	public String getAgentID();
	public double getAmountin();
	public int getOPS();
	public AgentStatus getState();
	public double getTransferred();
	public int getOPSCompleted();
	
	public void pauseAgent();
	public void resumeAgent();
	
	public BankAccount getAccount();
	public void finishThread();
}
