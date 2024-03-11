package pcd.lab03.liveness.accounts_exercise;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AccountManager {
	
	private final Account[] accounts;
	private final Lock[] locks = new Lock[5];

	public AccountManager(int nAccounts, int amount){
		accounts = new Account[nAccounts];
		for (int i = 0; i < accounts.length; i++){
			accounts[i] = new Account(amount);
			locks[i] = new ReentrantLock();
		}
	}
	
	public void transferMoney(int from,	int to, int amount) throws InsufficientBalanceException {
		int min = from;
		int max = to;
		if (from > to) {
			min = to;
			max = from;
		}

		var sender = accounts[min];
		var receiver = accounts[max];
		try {
			locks[min].lock();
			locks[max].lock();

			if (sender.getBalance() < amount) {
				throw new InsufficientBalanceException();
			}
			sender.debit(amount);
			receiver.credit(amount);
		} finally {
			locks[max].unlock();
			locks[min].unlock();
		}
	}

	public int getNumAccounts() {
		return accounts.length;
	}
}

