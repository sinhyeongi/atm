package Model;

//한 회원마다 계좌 3개까지 만들 수 있음 
public class Account {
	private String clientId;
	private String accNumber;
	private int money;
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
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccNumber() {
		return accNumber;
	}
	public void setAccNumber(String accNumber) {
		this.accNumber = accNumber;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	
	
}
