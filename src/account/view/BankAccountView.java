package account.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import account.controller.AgentController;
import account.controller.BankAccountController;
import account.controller.Controller;
import account.model.Agent;
import account.model.BankAccount;
import account.model.ListOfAccounts;
import account.model.Model;
import account.model.ModelEvent;
import account.model.ModelEvent.typeOfCurrency;



public class BankAccountView extends JFrameView {
	public static final String Withdraw = "Withdraw"; 
	public static final String Deposit = "Deposit"; 
	public static final String Dismiss = "Dismiss"; 
	private static JTextField textField = new JTextField(10); 
	private Handler handler = new Handler();
	private JTextField currentBalance = new JTextField(10); 

	final typeOfCurrency currency = ((BankAccount)getModel()).getCurrency();
	public BankAccountView(Model model, Controller controller){
		super(model,controller);
		
		this.setLayout(new GridLayout(4,4));
		int account = ((BankAccount)getModel()).getAccount();
		Integer.toString(account);
		String title = ((BankAccount)getModel()).getName() + " " + ((BankAccount)getModel()).getAccount();

		this.setTitle(title);
		textField.setText("0.0"); 
		currentBalance.setText(Double.toString(((BankAccount)getModel()).getBalance()));
		currentBalance.setEditable(false);
		
		JPanel editinLayout = new JPanel();
		editinLayout.setLayout(new FlowLayout());
		JPanel editAmount = new JPanel();
		editAmount.setLayout(new FlowLayout());
		
		JPanel withdrawPanel = new JPanel();
		JLabel labelwithdraw = new JLabel("Available funds: ");
		editAmount.add(withdrawPanel);
		withdrawPanel.add(labelwithdraw);
		withdrawPanel.add(currentBalance);
		
		
		JPanel editInPanel = new JPanel(); 
		JLabel editIn = new JLabel("Enter amount in {$ or Euros or Yen}");
		editAmount.add(editInPanel);
		editInPanel.add(editIn);
		editInPanel.add(textField);
		
		editinLayout.add(editAmount);
	
		this.getContentPane().add(editAmount);
		
		JPanel buttonPanel = new JPanel();	
	
		
		buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
		JButton withDrawButton = new JButton(Withdraw); 
		withDrawButton.addActionListener(handler); 
		
		JButton depositButton = new JButton(Deposit); 
		depositButton.addActionListener(handler); 
		
		JButton DismissButton = new JButton(Dismiss); 
		DismissButton.addActionListener(handler); 
	/*	
		JButton CreateWithrdrawAgentButton = new JButton(CreateWithrdrawAgent);
		CreateWithrdrawAgentButton.addActionListener(handler);
		
		JButton CreateDepositAgentButton = new JButton(CreateDepositAgent);
		CreateDepositAgentButton.addActionListener(handler);*/
		
		
		//textField.addActionListener(handler);
		//currentBalance.addActionListener(handler);
		
		
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		buttonPanel.add(withDrawButton, null);
		buttonPanel.add(depositButton, null);
		buttonPanel.add(DismissButton, null);
		
	//	JPanel agentPanel = new JPanel();
	//	this.getContentPane().add(agentPanel, BorderLayout.CENTER);
	//	agentPanel.add(CreateWithrdrawAgentButton, null);
	//	agentPanel.add(CreateDepositAgentButton, null);
		/*
		final Controller contr = controller;
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent evt) {
		    	for(AgentController agContr : agentControllers) agContr.operation(EditInView.Dismiss);
		         ((EditInController)contr).operation(Dismiss);
		         dispose();
		    }
		});*/
		pack();
		
	}
		
	

		
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((BankAccountController)getController()).operation(e.getActionCommand()); 
	    } 
		
	}
	
	public void getCurrency()
	{
		
	}
	

	
	public void modelChanged(ModelEvent event) {	
		if(currency==ModelEvent.typeOfCurrency.dollars)
		{
			System.out.println("currency is dollars");
			double balance = event.getBalance();
			currentBalance.setText(Double.toString(balance));
			System.out.println(balance);
		}
		if(currency==ModelEvent.typeOfCurrency.Yen)
		{
			System.out.println("currency is Yen");
			double balance = event.getBalance();
			balance = balance*BankAccount.yen;
			currentBalance.setText(Double.toString(balance));
			System.out.println(balance);
		}
		if(currency==ModelEvent.typeOfCurrency.Euros)
		{
			System.out.println("currency is Euros");
			double balance = event.getBalance();
			balance = balance*BankAccount.euros;
			currentBalance.setText(Double.toString(balance));
			System.out.println(balance);
		}
		textField.setText("0.0");
	}
	
	public void showError(String error){
		JOptionPane.showMessageDialog(this, error);
		
	}
	
	public double getTextFieldAmount(){
		double amount = 0;
		amount = Double.parseDouble(textField.getText());
		return amount;
		
	}
	
}