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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import account.controller.AgentController;
import account.controller.Controller;
import account.controller.ListController;
import account.model.Agent;
import account.model.BankAccount;
import account.model.ListOfAccounts;
import account.model.Model;
import account.model.ModelEvent;
import account.model.NegativeAccBalException;



public class AccountListView extends JFrameView {

	public static final String Editin$ = "Edit in $"; 
	public static final String EditinYen = "Edit in Yen"; 
	public static final String EditinEuros = "Edit in Euros"; 
	public static final String Save = "Save"; 
	public static final String Exit = "Exit";
	public static final String CreateWithrdrawAgent = "CreateWithdrawAgent";
	public static final String CreateDepositAgent = "CreateDepositAgent";
	public int selected = 0;
	private Handler handler = new Handler();
	
	public AccountListView(Model model, Controller controller) {
		super(model, controller);
		
		this.setTitle("Account Manager");
		this.setLayout(new GridLayout(4,4));
		
		JPanel accountLayout = new JPanel();
		accountLayout.setLayout(new FlowLayout());	
	//	setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		JPanel buttonPanel = new JPanel();
        JPanel labelPanel = new JPanel(); 
        JPanel fieldPanel = new JPanel();
		JLabel labelCombo = new JLabel("Accounts");
		Handler handler = new Handler();
		accountLayout.add(labelPanel);
		accountLayout.add(fieldPanel);
		
		int size = ((ListOfAccounts)getModel()).getSize();
				
		String[] accounts = new String[size];
		for(int i = 0; i < size;i++)
		{
			accounts[i]= ((ListOfAccounts)getModel()).getAccountAt(i).getAccount() + " " + ((ListOfAccounts)getModel()).getAccountAt(i).getName();
		}
		
		
		
		JComboBox<?> accountListBox = new JComboBox<Object>(accounts);
		accountListBox.setSelectedIndex(0);
		accountListBox.addActionListener(handler);

		
		labelPanel.add(labelCombo);
		fieldPanel.add(accountListBox);
		this.getContentPane().add(accountLayout, new GridLayout(4,4));
		
		
		//this.getcontentpane------add fields for things
		JButton edit$button = new JButton(Editin$); 
		edit$button.addActionListener(handler);
		JButton editYenButton = new JButton(EditinYen); 
		editYenButton.addActionListener(handler);
		JButton editEurosButton = new JButton(EditinEuros); 
		editEurosButton.addActionListener(handler);
		JButton saveButton = new JButton(Save); 
		saveButton.addActionListener(handler);
		JButton exitButton = new JButton(Exit); 
		exitButton.addActionListener(handler);
		
		JPanel agentButton = new JPanel();
		
		JButton CreateWithrdrawAgentButton = new JButton(CreateWithrdrawAgent);
		CreateWithrdrawAgentButton.addActionListener(handler);
		
		JButton CreateDepositAgentButton = new JButton(CreateDepositAgent);
		CreateDepositAgentButton.addActionListener(handler);

		agentButton.add(CreateWithrdrawAgentButton);
		agentButton.add(CreateDepositAgentButton);
		this.getContentPane().add(buttonPanel, BorderLayout.CENTER);
		this.getContentPane().add(agentButton,BorderLayout.SOUTH);
		buttonPanel.add(edit$button,null);
		buttonPanel.add(editYenButton, null);
		buttonPanel.add(editEurosButton, null);
		buttonPanel.add(saveButton, null);
		buttonPanel.add(exitButton, null);
		pack();
	}


	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}
	
	
	class Handler implements ActionListener{ 
		// Event handling is handled locally
		
		public void actionPerformed(ActionEvent e) {

			if(e.getActionCommand()== "comboBoxChanged")
			{
			JComboBox<?> accountList = (JComboBox<?>) e.getSource();	
			selected = accountList.getSelectedIndex();
				System.out.println(accountList.getSelectedItem() + " " + selected);
				((ListController)getController()).operation(e.getActionCommand(), selected);
			}
			else
			{
				((ListController)getController()).operation(e.getActionCommand(), selected);
			}
		}

	}
	
	
	public static void main(String [] args) { 
		
		ArrayList<BankAccount> accountList123 = new ArrayList<BankAccount>();//holds a list of bank accounts in the system
		

		String fileName = args[0]+ ".txt";
		System.out.println(args[0]);
		
		BankAccount account = new BankAccount(123, 512 , "Batman");
		System.out.println(account.getAccount() + " " + account.getBalance() + " " + account.getName());
		BankAccount acc = account.getBankAccount();
		
		
		System.out.println(acc.getAccount() + " " + acc.getBalance() + " " + acc.getName());
		account.deposit(5);
		
			String line = null;
	        try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = 
	                new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = 
	                new BufferedReader(fileReader);
	            while((line = bufferedReader.readLine()) != null) {
	            	Scanner s = new Scanner(line);
	            	int accNums = s.nextInt();
	            	String names = s.next();
	            	double balances = s.nextDouble();
	            	 try{
	            		  if(accNums < 0 || balances < 0)throw new NegativeAccBalException();
	            		    BankAccount accounts = new BankAccount(accNums,balances,names);	
	  	            		accountList123.add(accounts);
	            	 }catch (NegativeAccBalException e) {
	     				System.err.println("Negative value exception!");
	   	        	 JOptionPane.showMessageDialog(null, "Negative Value Exception, Please check file for negative balances or account numbers" , "Error",
	   	                     JOptionPane.ERROR_MESSAGE);
	   	        	}
	        	   	s.close();
	            }   
	            

	            // Always close files.
	            bufferedReader.close();         
	        }
	        catch(IOException ex) {
	            System.out.println(
	                "Unable to open file '" + 
	                fileName + "'");                 
	        }

	        
	        sortAccounts(accountList123);  
	        ListOfAccounts realist = new ListOfAccounts(accountList123);
	        for(int i = 0; i < accountList123.size();i++)
	        {
	        	System.out.print(accountList123.get(i).getBankAccount()+ " : real list -" );
	        	System.out.println(realist.getAccountAt(i).getBankAccount());
	        	
	        }
	        
	        ListController listControl = new ListController();
	        listControl.setModel(realist);
	        
	        SwingUtilities.invokeLater(new Runnable() {
			      @Override
			      public void run() {
			    	  AccountListView accountListView = new AccountListView(realist, listControl);
			    	  listControl.setView(accountListView);
			    	  accountListView.setVisible(true);
			      }
			    });
	        

	}

	public static void sortAccounts(ArrayList<BankAccount> list) {
		quickSort(list, 0, list.size() - 1);
	}

	private static void quickSort(ArrayList<BankAccount> list, int low, int high) {
		if (list == null || list.size() == 0)
			return;

		if (low >= high)
			return;

		// pick the pivot
		int middle = low + (high - low) / 2;
		int pivot = list.get(middle).getAccount();

		// make left < pivot and right > pivot
		int i = low, j = high;
		while (i <= j) {
			while (list.get(i).getAccount() < pivot) {
				i++;
			}

			while (list.get(j).getAccount() > pivot) {
				j--;
			}

			if (i <= j) {
				BankAccount temp = list.get(i);
				list.set(i, list.get(j));
				list.set(j, temp);
				i++;
				j--;
			}
		}

		// recursively sort two sub parts
		if (low < j)
			quickSort(list, low, j);

		if (high > i)
			quickSort(list, i, high);

	}	
}
