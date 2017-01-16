package account.controller;

import account.view.BankAccountView;


import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;

import account.model.Agent;
import account.model.AgentCreator;
import account.model.BankAccount;
import account.model.ListOfAccounts;
import account.model.ModelEvent.typeOfCurrency;
import account.view.AccountListView;
import account.view.AgentView;

public class ListController extends AbstractController{
	public void operation(String option, int selectedAccount) {
		if(option.equals(AccountListView.Editin$)){
			final BankAccountController cont = new BankAccountController();
			System.out.println("selected account " + selectedAccount);
			final BankAccount selected = ((ListOfAccounts)getModel()).getAccountAt(selectedAccount);

			cont.setModel(selected);	
			selected.setCurrency(typeOfCurrency.dollars);
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {	    	  
			    	  BankAccountView bView = new BankAccountView(selected, cont);
			    	  cont.setView(bView);
			    	  bView.setVisible(true);
			      }
			    });
		}else if(option.equals(AccountListView.EditinYen)){
			final BankAccountController cont = new BankAccountController();
			System.out.println("selected account " + selectedAccount);
			final BankAccount selected = ((ListOfAccounts)getModel()).getAccountAt(selectedAccount);

			selected.setCurrency(typeOfCurrency.Yen);
			cont.setModel(selected);	
			
			SwingUtilities.invokeLater(new Runnable() {
			      @Override
			      public void run() {
			    	  BankAccountView bView = new BankAccountView(selected, cont);
			    	  cont.setView(bView);
			    	  bView.setVisible(true);
			    	  selected.addModelListener(bView);
			    	  
			      }
			    });
		}else if(option.equals(AccountListView.EditinEuros)){
			final BankAccountController cont = new BankAccountController();
			System.out.println("selected account " + selectedAccount);
			final BankAccount selected = ((ListOfAccounts)getModel()).getAccountAt(selectedAccount);
			selected.setCurrency(typeOfCurrency.Euros);
			cont.setModel(selected);	
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {
			    	  BankAccountView bView = new BankAccountView(selected, cont);
			    	  cont.setView(bView);
			    	  bView.setVisible(true);
			    	  selected.addModelListener(bView);
			      }
			    });
		}
		else if(option.equals(AccountListView.Exit)){
			((ListOfAccounts)getModel()).Exit();
		}else if(option.equals(AccountListView.Save)){
			((ListOfAccounts)getModel()).Save();
		}else if(option.equals(AccountListView.CreateDepositAgent)){
			final AgentController cont = new AgentController();
			final BankAccount selected = ((ListOfAccounts)getModel()).getAccountAt(selectedAccount);
			final Agent newAgent = AgentCreator.createDepositAgent(((ListOfAccounts)getModel()).getAccountAt(selectedAccount));
			System.out.println("Agent for Account: " + selected.getAccount());
			cont.setModel(newAgent);
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {
			    	  AgentView aView = new AgentView(newAgent, cont);
			    	  cont.setView(aView);
			    	  aView.setVisible(true);
			    	
			      }
			    });
		}else if(option.equals(AccountListView.CreateWithrdrawAgent)){
			final AgentController cont = new AgentController();
			final BankAccount selected = ((ListOfAccounts)getModel()).getAccountAt(selectedAccount);
			final Agent newAgent = AgentCreator.createWithdrawAgent(((ListOfAccounts)getModel()).getAccountAt(selectedAccount));
			System.out.println("Agent for Account: " + selected.getAccount());
			cont.setModel(newAgent);
			SwingUtilities.invokeLater(new Runnable() {
			      public void run() {
			    	  AgentView aView = new AgentView(newAgent, cont);
			    	  cont.setView(aView);
			    	  aView.setVisible(true);
			    	  selected.addModelListener(aView);

			      }
			    });
		}
	}

}
