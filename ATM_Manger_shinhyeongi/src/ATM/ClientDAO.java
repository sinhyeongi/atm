package ATM;

public class ClientDAO {
	Client[] client;
	
	//유저 번호 업데이트
	void UpdataUserNo() {
		if(client == null) return;
		for(int i = 0 ; i < client.length; i++) {
			client[i].clientNo = 1001+i;
		}
	}
	//불로오기 시
	void SetData(String data) {
		if(data == null || data.length() == 0)return;
		String[] user = data.split("\n");
		client = new Client[user.length];
		for(int i = 0 ; i < user.length; i++) {
			String t[] = user[i].split("/");
			client[i] = new Client(t[1],t[2],t[3],Integer.parseInt(t[0]));
		}
	}
	//회원 탈퇴
	void DeleteUser(String id) {
		if(client.length == 1) {
			client = null;
			System.out.println("회원탈퇴 완료");
			return;
		}
		Client[] copy = client;
		client = new Client[copy.length - 1];
		int idx = 0;
		for(int i = 0 ; i < copy.length; i++) {
			if(!copy[i].id.equals(id)) {
				client[idx++] = copy[i];
			}
		}
		UpdataUserNo();
		System.out.println("회원탈퇴 완료");
	}
	// 로그인
	String Login(String id, String pw) {
		if (client == null) {
			return "사용자";
		}
		String s = "사용자";
		for (int i = 0; i < client.length; i++) {
			if (client[i].id.equals(id) && client[i].pw.equals(pw)) {
				s = client[i].id;
			}
		}
		return s;
	}
	//전체 회원 정보보내기
	String GetAllUser() {
		if(client == null) return "";
		String data ="";
		for(int i = 0 ; i < client.length; i++) {
			data += client[i].clientNo + "/"+client[i].id+"/"
		+client[i].pw+"/"+client[i].name+"\n";
		}
		data = data.substring(0,data.length()-1);
		return data;
	}
	//비밀번호 변경
	void ChangePw(String id, String pw) {
		if(client == null) return;
		for(int i = 0 ; i < client.length; i++) {
			if(id.equals(client[i].id)) {
				client[i].pw = pw;
				break;
			}
		}
		System.out.println("비밀번호 변경 완료");
	}
	//이름 변경
	void ChangeName(String id, String name) {
		if(client == null) return;
		for(int i = 0 ; i < client.length; i++) {
			if(id.equals(client[i].id)) {
				client[i].name = name;
				break;
			}
		}
		System.out.println("이름 변경 완료");
	}
	//아이디 값 받아서 이름 리턴
	String getName(String id) {
		if(client == null) return "";
		String s = "";
		for(int i = 0 ; i < client.length; i++)
			if(id.equals(client[i].id)) {
				s = client[i].name;
				break;
			}
		return s;
	}
	//client 길이 리턴
	int getClientLength() {
		if(client == null) return -1;
		return client.length;
	}
	// 아이디 값 받아서 클라이언트에 있는지 체크
	boolean CheckId(String id) {
		if (client == null)
			return false;
		for (int i = 0; i < client.length; i++)
			if (id.equals(client[i].id))
				return true;
		return false;
	}
	//새로운 아이디 생성
	void NewClient(String id,String pw, String name) {
		
		if(client == null) {
			client = new Client[1];
			client[0] = new Client(id,pw,name,1001);
		}else {
			Client copy[] = client;
			client = new Client[copy.length + 1];
			for(int i = 0 ; i < copy.length; i++) {
				client[i] = copy[i];
			}
			client[client.length - 1] = new Client(id,pw,name,client[client.length - 2].clientNo + 1);
		}
		System.out.println("추가 완료");
	}
}
