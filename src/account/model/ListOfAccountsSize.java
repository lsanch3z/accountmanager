package account.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ListOfAccountsSize {

	@Test
	public void test() {
		List<BankAccount> acc = new ArrayList<BankAccount>();
		BankAccount batCount = new BankAccount(123,512,"BatMan");
		acc.add(batCount);
		acc.add(batCount);
		ListOfAccounts accounts = new ListOfAccounts(acc);
		int sizeOutput = accounts.getSize();
		assertEquals(2, sizeOutput);
	}

}
