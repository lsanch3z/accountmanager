package account.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import account.controller.AgentController;
import account.controller.Controller;
import account.model.Agent;
import account.model.AgentStatus;
import account.model.BankAccount;
import account.model.Model;
import account.model.ModelEvent;

public class AgentView extends JFrameView {
	private static JTextField AgentID = new JTextField(5);
	private static JTextField Amountin$ = new JTextField(5);
	private static JTextField OperationsperSecond = new JTextField(5);
	public static final String StartAgent = "Start Agent"; 
	public static final String Dismiss = "Dismiss"; 
	private Handler handler = new Handler();
	
	public static final String StopAgent = "Stop Agent"; 
	public static final String DismissAgent = "DismissAgent"; 
	private static JTextField AmountInWorking = new JTextField(5);
	private static JTextField OperationsWorking = new JTextField(5);
	private static JTextField State = new JTextField(5);
	private static JTextField AmountTransfered = new JTextField(5);
	private static JTextField OperationsComplete = new JTextField(5);
	JButton dismissAg = new JButton(DismissAgent);
	JPanel combined = new JPanel();
	JPanel combinedSecond = new JPanel();

	
	public AgentView(Model model, Controller controller) {
		super(model, controller);

		this.combined = new JPanel();
		this.combinedSecond = new JPanel();
		AmountTransfered.setText("0.0");
		JPanel AgentLayout = new JPanel();
		JPanel buttonPanel = new JPanel();
		this.setLayout(new GridLayout(4,4));
		this.setLayout(new CardLayout());
		combined.setLayout(new GridLayout(4,4));
		BankAccount account = ((Agent)getModel()).getAccount();
		String title = account.getName() + " " + account.getAccount();
		this.setTitle(title);

		
		AgentLayout.setLayout(new FlowLayout());
		JPanel textLayout = new JPanel();
		textLayout.setLayout(new FlowLayout());
		
		JPanel idFields = new JPanel();
		idFields.setLayout(new FlowLayout());
		JPanel amountInfields = new JPanel();
		amountInfields.setLayout(new FlowLayout());
		JPanel OpFields = new JPanel();
		OpFields.setLayout(new FlowLayout());

		
		JLabel idAgent = new JLabel("Agent ID: ");
		JLabel amountIn = new JLabel("Amount In $: ");
		JLabel OperationsPer = new JLabel("Operations Per Second: ");
		
		
		idFields.add(idAgent);
		idFields.add(AgentID);
		
		amountInfields.add(amountIn);
		amountInfields.add(Amountin$);
		
		OpFields.add(OperationsPer);
		OpFields.add(OperationsperSecond);
		
		textLayout.add(idFields);
		textLayout.add(amountInfields);
		textLayout.add(OpFields);
		
		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
		JButton startagent = new JButton(StartAgent);
		startagent.addActionListener(handler);
		JButton dismiss = new JButton(Dismiss);
		dismiss.addActionListener(handler);
		
		buttonPanel.add(startagent,null);
		buttonPanel.add(dismiss,null);
		
		AgentLayout.add(textLayout);
		combined.add(AgentLayout);
		combined.add(buttonPanel);
	
		this.getContentPane().add(combined, BorderLayout.NORTH);
		
		combinedSecond.setLayout(new GridLayout(4,4));
		String idName = ((Agent)getModel()).getAgentID();
		combinedSecond.setSize(1000,600);
		System.out.println("new starting agent window");
		JPanel buttonPanel2 = new JPanel();

		JPanel textLayout2 = new JPanel();
		textLayout2.setLayout(new FlowLayout());
		
		JPanel amountInfieldsWorking = new JPanel();
		amountInfieldsWorking.setLayout(new FlowLayout());
		
		JPanel OperationsWorkingfields = new JPanel();
		OperationsWorkingfields.setLayout(new FlowLayout());
		
		JPanel statefields = new JPanel();
		statefields.setLayout(new FlowLayout());
		
		JPanel AmountTrans = new JPanel();
		AmountTrans.setLayout(new FlowLayout());
		
		JPanel opsComplete = new JPanel();
		opsComplete.setLayout(new FlowLayout());
		
		JPanel workingText = new JPanel();
		workingText.setLayout(new FlowLayout());

		JLabel state = new JLabel("State: ");
		State.setEditable(false);
		State.setText(AgentStatus.NA.toString());
		statefields.add(state);
		statefields.add(State);
		
		
		JLabel amountTransfered = new JLabel("Amount Transfered: ");
		AmountTransfered.setEditable(false);
		AmountTrans.add(amountTransfered);
		AmountTrans.add(AmountTransfered);
		
		JLabel operationsComplete = new JLabel("Operations Complete: ");
		OperationsComplete.setEditable(false);
		opsComplete.add(operationsComplete);
		opsComplete.add(OperationsComplete);
		
		JLabel amountinworking = new JLabel("Amount In $: ");
		AmountInWorking.setEditable(false);
		amountInfieldsWorking.add(amountinworking);
		amountInfieldsWorking.add(AmountInWorking);
		((Agent)getModel()).getAgentID();
		double amountin = ((Agent)getModel()).getAmountin();
		AmountInWorking.setText(String.valueOf(amountin));
		
		JLabel opscompleteworking = new JLabel("Operations Per Second");
		OperationsWorking.setEditable(false);
		OperationsWorkingfields.add(opscompleteworking);
		OperationsWorkingfields.add(OperationsWorking);
		int ops = ((Agent)getModel()).getOPS();
		OperationsWorking.setText(String.valueOf(ops));
		
		textLayout2.add(amountInfieldsWorking);
		textLayout2.add(OperationsWorkingfields);
		textLayout2.add(statefields);
		textLayout2.add(AmountTrans);
		textLayout2.add(opsComplete);
		
		textLayout2.add(workingText);
		
		buttonPanel2.setLayout(new GridLayout(4, 4, 10, 10));
		JButton stop = new JButton(StopAgent);
		stop.addActionListener(handler);

		dismissAg.setEnabled(false);
		dismissAg.addActionListener(handler);
		buttonPanel2.add(stop,null);
		buttonPanel2.add(dismissAg,null);
		
		combinedSecond.add(textLayout2);
		combinedSecond.add(buttonPanel2);
		this.getContentPane().add(combinedSecond, BorderLayout.NORTH);
		combinedSecond.setVisible(false);
		
		
		pack();
		
	}
	
	
	
	class Handler implements ActionListener { 
		// Event handling is handled locally
	
		public void actionPerformed(ActionEvent e) {
			((AgentController)getController()).operation(e.getActionCommand());

		}
		
	}
	
	
	public void modelChanged(ModelEvent me) {
		State.setText((me.getAgStatus()).toString());
		AmountTransfered.setText(Double.toString(me.getTransfered()));
		OperationsComplete.setText(Double.toString(me.getopsCompleted()));
		AmountInWorking.setText(Double.toString(me.getAmountIn()));
		OperationsWorking.setText(Integer.toString(me.getOps()));

		
	}
	
	public void startingAgent(){
		String idName = ((Agent)getModel()).getAgentID();
		this.setTitle(idName);
		this.combined.setVisible(false);
		this.combinedSecond.setVisible(true);
		/*
		workingAgent.setTitle(idName);
		workingAgent.setLayout(new GridLayout(4,4));
		workingAgent.setSize(1000,600);
		System.out.println("new starting agent window");
		JPanel WorkingAgent = new JPanel();
		JPanel buttonPanel2 = new JPanel();

		JPanel textLayout2 = new JPanel();
		textLayout2.setLayout(new FlowLayout());
		
		JPanel amountInfieldsWorking = new JPanel();
		amountInfieldsWorking.setLayout(new FlowLayout());
		
		JPanel OperationsWorkingfields = new JPanel();
		OperationsWorkingfields.setLayout(new FlowLayout());
		
		JPanel statefields = new JPanel();
		statefields.setLayout(new FlowLayout());
		
		JPanel AmountTrans = new JPanel();
		AmountTrans.setLayout(new FlowLayout());
		
		JPanel opsComplete = new JPanel();
		opsComplete.setLayout(new FlowLayout());
		
		JPanel workingText = new JPanel();
		workingText.setLayout(new FlowLayout());

		JLabel state = new JLabel("State: ");
		State.setEditable(false);
		State.setText(AgentStatus.NA.toString());
		statefields.add(state);
		statefields.add(State);
		
		
		JLabel amountTransfered = new JLabel("Amount Transfered: ");
		AmountTransfered.setEditable(false);
		AmountTrans.add(amountTransfered);
		AmountTrans.add(AmountTransfered);
		
		JLabel operationsComplete = new JLabel("Operations Complete: ");
		OperationsComplete.setEditable(false);
		opsComplete.add(operationsComplete);
		opsComplete.add(OperationsComplete);
		
		JLabel amountinworking = new JLabel("Amount In $: ");
		AmountInWorking.setEditable(false);
		amountInfieldsWorking.add(amountinworking);
		amountInfieldsWorking.add(AmountInWorking);
		((Agent)getModel()).getAgentID();
		double amountin = ((Agent)getModel()).getAmountin();
		AmountInWorking.setText(String.valueOf(amountin));
		
		JLabel opscompleteworking = new JLabel("Operations Per Second");
		OperationsWorking.setEditable(false);
		OperationsWorkingfields.add(opscompleteworking);
		OperationsWorkingfields.add(OperationsWorking);
		int ops = ((Agent)getModel()).getOPS();
		OperationsWorking.setText(String.valueOf(ops));
		
		textLayout2.add(amountInfieldsWorking);
		textLayout2.add(OperationsWorkingfields);
		textLayout2.add(statefields);
		textLayout2.add(AmountTrans);
		textLayout2.add(opsComplete);
		
		textLayout2.add(workingText);
		
		buttonPanel2.setLayout(new GridLayout(4, 4, 10, 10));
		JButton stop = new JButton(StopAgent);
		stop.addActionListener(handler);
	//	JButton dismissAg = new JButton(DismissAgent);
		dismissAg.setEnabled(false);
		dismissAg.addActionListener(handler);
		buttonPanel2.add(stop,null);
		buttonPanel2.add(dismissAg,null);
		WorkingAgent.add(textLayout2);	
		//WorkingAgent.setVisible(false);
		//buttonPanel2.setVisible(false);

		workingAgent.getContentPane().add(WorkingAgent, BorderLayout.NORTH);
		workingAgent.getContentPane().add(buttonPanel2, BorderLayout.SOUTH);
		workingAgent.setVisible(true);*/
		
	}
	
	

	
	public void closeStarter() {
		this.setVisible(false);
		
	}
	
	public void closeworkingAgent(){
		this.setVisible(false);
	}

	public void enableDismissAgent(){
		dismissAg.setEnabled(true);
	}
	public static double getAmountin() {
		double amount = Double.parseDouble(Amountin$.getText());
		return amount;
	}


	public static int getOpS() {
		int ops = Integer.parseInt(OperationsperSecond.getText());
		return ops;
	}


	public static String getIDAgent() {
		String id = AgentID.getText();
		return id;
	}

	public static double getTransfered() {
		double transfer = Double.parseDouble(AmountTransfered.getText());
		System.out.println(transfer);
		return transfer;
	}
	
	
}
