package DAO;

import java.util.ArrayList;

import Model.Account;

public class AccountDAO {
	private ArrayList<Account> account;
	public AccountDAO(){
		account = new ArrayList<Account>();
	}
	
	//불러오기
	public void SetData(String data) {
		if(data == null || data.length() == 0)return;
		String[] user = data.split("\n");
		if(account == null) account = new ArrayList<Account>();
		for(int i = 0 ; i < user.length; i++) {
			String t[] = user[i].split("/");
			account.add(new Account(t[0],t[1],Integer.parseInt(t[2])));
		}
		System.out.println("불러오기 완료");
	}
	//id값으로 있는 계좌 갯수 리턴
	public int UserAccountCount(String id) {
		if(account == null) return 0;
		int c = 0;
		for(int i = 0; i < account.size(); i++) {
			if(id.equals(account.get(i).getClientId()))
				c++;
		}
		return c;
	}
	//전체 데이터 스트링으로 리턴
	public String GetAllAccountData() {
		if(account == null) return"";
		String data ="";
		for(int i = 0 ; i < account.size(); i++) {
			data += account.get(i).getClientId()+"/"+account.get(i).getAccNumber()+"/"+account.get(i).getMoney()+"\n";
		}
		data = data.substring(0,data.length() -1);
		return data;
	}
	//유저 탈퇴시
	public void DeleteUser(String id) {
		int count = UserAccountCount(id);
		if(count == 0) return;
		for(int i = 0 ; i < account.size(); i++) {
			if(CheckIdAndAcc(id,account.get(i)) == false) {
				if(account.size() == 1) {
					account.clear();
					break;
				}
				account.remove(i);
				i--;
			}
		}
	}
	//해당 아이디에 계좌와 계좌 잔액 리턴
	public 	String UserAllAccount(String id) {
			if(account == null )return "";
			String data = String.format("%7s\t%s\n","계좌번호","계좌잔액");
			for(int i = 0 ; i < account.size(); i++) {
				if(CheckIdAndAcc(id,account.get(i))) {
					data += account.get(i).getAccNumber() + "\t"+account.get(i).getMoney()+"원\n";
				}
			}
			return data;
		}
	//새로운 계좌 추가
	public boolean CheckAccount(String acc) {
		if(account == null) return false;
		for(int i = 0 ; i < account.size(); i++) {
			if(account.get(i).getAccNumber().equals(acc)) {
				return true;
			}
		}
		return false;
	}
	//이체
	public void TransferMoney(String id, String acc1,String acc2,int Money) {
		if(account == null) return;
		for(int i = 0 ; i < account.size(); i++) {
			if(account.get(i).getAccNumber().equals(acc1) && account.get(i).getClientId().equals(id)) {
				account.get(i).setMoney(account.get(i).getMoney()- Money);
			}else if(account.get(i).getAccNumber().equals(acc2)) {
				account.get(i).setMoney(account.get(i).getMoney() + Money);
			}
		}
		System.out.println("[이체 완료]");
	}
	//아이디 와 계좌번호가 같은 것이 있는지 체크
	public boolean CheckAccount(String acc,String id) {
		if(account == null) return false;
		for(int i = 0 ; i < account.size(); i++) {
			if(CheckIdAndAcc(id, acc, account.get(i))) {
				return true;
			}
		}
		return false;
	}
	//아이디 값으로 한개의 계좌 삭제
	public void DeleteOneAccount(String id, String acc) {
		if(account.size() == 1) {
			account.clear();
			System.out.println(acc+"계좌 삭제 완료");
			return;
		}
		for(int i = 0 ; i < account.size(); i++) {
			if(CheckIdAndAcc(id, acc, account.get(i))) {
				account.remove(i);
				break;
			}
		}
		System.out.println(acc+"계좌 삭제 완료");
	}
	//새로운 계좌 생성
	public void NewAccount(String Id, String accNumber) {
		if(account == null) {
			account = new ArrayList<Account>();
		}
		account.add(new Account(Id,accNumber));
		System.out.println("계좌 추가 완료");
	}
	//계좌 잔액 출금 
	public void OneUserSubMoney(int money,String id, String acc) {
		if(account == null )return;
		for(int i = 0 ; i < account.size(); i++) {
			if(CheckIdAndAcc(id, acc, account.get(i))) {
				account.get(i).setMoney(account.get(i).getMoney() - money) ;
				break;
			}
		}
		System.out.println("[ 출금 완료 ]");
	}
	//아이다와 계좌번호가 같은지 확인 (반복문 용도)
	public boolean CheckIdAndAcc(String id,String acc,Account account) {
		if(account.getAccNumber().equals(acc) && account.getClientId().equals(id))
			return true;
		return false;
	}
	//아이디가 같은지 확인 (반복문)
	public boolean CheckIdAndAcc(String id,Account account) {
		if(account.getClientId().equals(id))
			return true;
		return false;
	}
	//유저 한명의 계좌 잔액 리턴
	public int getOneUserMoney(String id, String acc) {
		if(account == null) return 0;
		for(int i = 0 ; i < account.size(); i++) {
			if(CheckIdAndAcc(id, acc, account.get(i))) {
				return account.get(i).getMoney();
			}
		}
		return 0;
	}
	//입금
	public void PlustAccountMoney(int money, String id,String acc) {
		if(account == null) return;
		for(int i = 0 ; i < account.size(); i++) {
			if(CheckIdAndAcc(id, acc, account.get(i))) {
				account.get(i).setMoney(account.get(i).getMoney() + money) ;
				break;
			}
		}
		System.out.println("["+money+"입금 완료]");
	}
}
