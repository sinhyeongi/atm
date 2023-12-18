package ATM;

public class Client {
	int clientNo; //자동 1증가
	String id;
	String pw;
	String name;
	public Client() {
		// TODO Auto-generated constructor stub
	}
	public Client(String id,String pw,String name,int no) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.pw = pw;
		this.name = name;
		clientNo = no;
	}
}
