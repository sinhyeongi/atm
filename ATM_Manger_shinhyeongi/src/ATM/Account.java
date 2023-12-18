package ATM;

//한 회원마다 계좌 3개까지 만들 수 있음 
public class Account {
	String clientId;
	String accNumber;
	int money;
	public Account() {
		// TODO Auto-generated constructor stub
	}
	public Account(String id, String number) {
		// TODO Auto-generated constructor stub
		clientId = id;
		accNumber = number;
	}
	public Account(String id, String number,int money) {
		// TODO Auto-generated constructor stub
		clientId = id;
		accNumber = number;
		this.money = money;
	}
}
