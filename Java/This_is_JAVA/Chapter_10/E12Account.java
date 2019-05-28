package Chapter_10;

public class E12Account {
	private long balance;
	
	public E12Account() {}
	
	public long getBalance() {
		return balance;
	}
	
	public void deposit(int money){
		balance += money;
	}
	
	public void withdraw(int money) throws E12BalanceInsufficientException {
		if(balance < money){
			throw new E12BalanceInsufficientException("잔고부족:" + (money-balance) + "모자람");
		}
		balance -= money;
	}
}
