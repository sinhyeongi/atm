package Model;

public class Client {
	private int clientNo; //자동 1증가
	private String id;
	private String pw;
	private String name;
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
	public int getClientNo() {
		return clientNo;
	}
	public void setClientNo(int clientNo) {
		this.clientNo = clientNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
