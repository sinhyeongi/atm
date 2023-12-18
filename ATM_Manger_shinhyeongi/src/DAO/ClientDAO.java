package DAO;

import java.util.ArrayList;

import Model.Client;

public class ClientDAO {
	private ArrayList<Client> client;
	
	public ClientDAO() {
		client = new ArrayList<Client>();
	}
	//유저 번호 업데이트
	public void UpdataUserNo() {
		if(client == null) return;
		for(int i = 0 ; i < client.size(); i++) {
			client.get(i).setClientNo(1001+i); 
		}
	}
	//불로오기 시
	public void SetData(String data) {
		if(data == null || data.length() == 0)return;
		String[] user = data.split("\n");
		if(client.size() != 0) client.clear();
		for(int i = 0 ; i < user.length; i++) {
			String t[] = user[i].split("/");
			client.add(new Client(t[1],t[2],t[3],Integer.parseInt(t[0])));
		}
	}
	//회원 탈퇴
	public void DeleteUser(String id) {
		if(client.size() == 1) {
			client.clear();
			System.out.println("회원탈퇴 완료");
			return;
		}
		for(int i = 0 ; i < client.size(); i++) {
			if(client.get(i).getId().equals(id)) {
				client.remove(i);
				break;
			}
		}
		UpdataUserNo();
		System.out.println("회원탈퇴 완료");
	}
	// 로그인
	public String Login(String id, String pw) {
		if (client == null) {
			return "사용자";
		}
		String s = "사용자";
		for (int i = 0; i < client.size(); i++) {
			if (client.get(i).getId().equals(id) && client.get(i).getPw().equals(pw)) {
				s = client.get(i).getId();
			}
		}
		return s;
	}
	//전체 회원 정보보내기
	public String GetAllUser() {
		if(client == null) return "";
		String data ="";
		for(int i = 0 ; i < client.size(); i++) {
			data += client.get(i).getClientNo() + "/"+client.get(i).getId()+"/"
		+client.get(i).getPw()+"/"+client.get(i).getName()+"\n";
		}
		data = data.substring(0,data.length()-1);
		return data;
	}
	//비밀번호 변경
	public void ChangePw(String id, String pw) {
		if(client == null) return;
		for(int i = 0 ; i < client.size(); i++) {
			if(id.equals(client.get(i).getId())) {
				client.get(i).setPw(pw);
				break;
			}
		}
		System.out.println("비밀번호 변경 완료");
	}
	//이름 변경
	public void ChangeName(String id, String name) {
		if(client == null) return;
		for(int i = 0 ; i < client.size(); i++) {
			if(id.equals(client.get(i).getId())) {
				client.get(i).setName(name)  ;
				break;
			}
		}
		System.out.println("이름 변경 완료");
	}
	//아이디 값 받아서 이름 리턴
	public String getName(String id) {
		if(client == null) return "";
		String s = "";
		for(int i = 0 ; i < client.size(); i++)
			if(id.equals(client.get(i).getId())) {
				s = client.get(i).getName();
				break;
			}
		return s;
	}
	//client 길이 리턴
	public int getClientLength() {
		if(client.size() == 0) return -1;
		return client.size();
	}
	// 아이디 값 받아서 클라이언트에 있는지 체크
	public boolean CheckId(String id) {
		if (client == null)
			return false;
		for (int i = 0; i < client.size(); i++)
			if (id.equals(client.get(i).getId()))
				return true;
		return false;
	}
	//새로운 아이디 생성
	public void NewClient(String id,String pw, String name) {
		client.add(new Client(id,pw,name,client.size()+1001));
		System.out.println("추가 완료");
	}
}
