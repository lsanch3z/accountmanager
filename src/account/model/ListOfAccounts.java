package account.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author Leonel
 *
 */
public class ListOfAccounts extends AbstractModel {
	List<BankAccount> listOfAccounts;
	
	
	/**
	 * @param listOfAccounts
	 */
	public ListOfAccounts(List<BankAccount> listOfAccounts){
		this.listOfAccounts = listOfAccounts;
		
	}
	
	/**
	 * @param index
	 * @return
	 */
	public BankAccount getAccountAt(int index)
	{
		return listOfAccounts.get(index);
	}

	/**
	 * @return
	 */
	public int getSize() {
		return listOfAccounts.size();
		
	}
	
	
	/**
	 * 
	 */
	public void Save() {

		String fileName = "accounts.txt";

		try {

			FileWriter fileWriter = new FileWriter(fileName);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);


			for (int i = 0; i < listOfAccounts.size(); i++) {
				bufferedWriter.write(listOfAccounts.get(i).getAccount() + " " + listOfAccounts.get(i).getName() + " "
						+ listOfAccounts.get(i).getBalance());
				bufferedWriter.newLine();
			}

			bufferedWriter.close();
		} catch (IOException ex) {
			System.out.println("Error writing to file '" + fileName + "'");
		}
	}

	/**
	 * 
	 */
	public void Exit() {
		Save();
		System.exit(0);
	}
	
	
}