package account.controller;

import account.model.BankAccount;
import account.model.ModelEvent.typeOfCurrency;
import account.model.OverdrawException;
import account.view.BankAccountView;
import account.view.JFrameView;


public class BankAccountController extends AbstractController  {
	public BankAccountController(){
	}
		public void operation(String option){
			if(option.equals(BankAccountView.Deposit)){
				double amount = ((BankAccountView)getView()).getTextFieldAmount();
				((BankAccount)getModel()).deposit(amount);
				
			}else if(option.equals(BankAccountView.Withdraw)){
				double amount = ((BankAccountView)getView()).getTextFieldAmount();
				try{
					((BankAccount)getModel()).withdraw(amount);
			}catch (OverdrawException e){
				System.out.println(e.getOverdrawException(((BankAccount)getModel()).getBalance(),amount));
				((BankAccountView)getView()).showError(e.getOverdrawException(((BankAccount)getModel()).getBalance(),amount));
			}	
			}
			else if(option.equals(BankAccountView.Dismiss)){
				BankAccountView views = (BankAccountView)getView();
				views.dispose();
			}

			
	}
}

