package account.model;

import java.util.ArrayList;
import java.util.List;

public abstract class AgentCreator{
	private static  int depositCounter = 0;
	private static int withdrawCounter = 0;
	
	 private static List<Thread> agentControllers = new ArrayList<Thread>();
	 public static Agent createDepositAgent(BankAccount account)
	 {
		 DepositAgent depAgent = new DepositAgent(account);
		 Thread depThread = new Thread(depAgent);
		 depAgent.setName(account.getName()+depositCounter);
		 depThread.setName(account.getName()+depositCounter);
		 agentControllers.add(depThread);
		 depositCounter++;
		 depThread.start();
		 return depAgent;
		  
	 }

	 
	 public static Agent createWithdrawAgent(BankAccount account)
	 {
		 
		 WithdrawAgent wAgent = new WithdrawAgent(account);
		 Thread wThread = new Thread(wAgent);
		 wAgent.setName(account.getName()+withdrawCounter);
		 wThread.setName(account.getName()+withdrawCounter);
		 agentControllers.add(wThread);
		 withdrawCounter++;
		 wThread.start();
		 return wAgent;
		 
		 
	 }
	 
	 
	 public static void finishThreads(){
		 for(Thread t: agentControllers){
			 t.interrupt();
		 }
	 }
	 
	 
	 
}
