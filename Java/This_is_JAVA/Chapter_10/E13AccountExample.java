package Chapter_10;

public class E13AccountExample {
	public static void main(String args[]){
		E12Account account = new E12Account();
		
		account.deposit(10000);
		System.out.println("예금액: " + account.getBalance());
		
		try{
			account.withdraw(30000);
		} catch(E12BalanceInsufficientException e){
			String message = e.getMessage();
			System.out.println(message);
			System.out.println();
			e.printStackTrace();
		}
	}
}
