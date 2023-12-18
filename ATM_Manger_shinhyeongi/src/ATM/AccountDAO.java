package ATM;

public class AccountDAO {
	Account[] account;
	
	
	//불러오기
	void SetData(String data) {
		if(data == null || data.length() == 0)return;
		String[] user = data.split("\n");
		account = new Account[user.length];
		for(int i = 0 ; i < user.length; i++) {
			String t[] = user[i].split("/");
			account[i] = new Account(t[0],t[1],Integer.parseInt(t[2]));
		}
		System.out.println("불러오기 완료");
	}
	//id값으로 있는 계좌 갯수 리턴
	int UserAccountCount(String id) {
		if(account == null) return 0;
		int c = 0;
		for(int i = 0; i < account.length; i++) {
			if(id.equals(account[i].clientId))
				c++;
		}
		return c;
	}
	//전체 데이터 스트링으로 리턴
	String GetAllAccountData() {
		if(account == null) return"";
		String data ="";
		for(int i = 0 ; i < account.length; i++) {
			data += account[i].clientId+"/"+account[i].accNumber+"/"+account[i].money+"\n";
		}
		data = data.substring(0,data.length() -1);
		return data;
	}
	//유저 탈퇴시
	void DeleteUser(String id) {
		int count = UserAccountCount(id);
		if(count == 0) return;
		Account[] copy = account;
		account = new Account[copy.length - count];
		int idx = 0;
		for(int i = 0 ; i < copy.length; i++) {
			if(CheckIdAndAcc(id,copy[i]) == false) {
				account[idx++] = copy[i];
			}
		}
	}
	//해당 아이디에 계좌와 계좌 잔액 리턴
		String UserAllAccount(String id) {
			if(account == null )return "";
			String data = String.format("%7s\t%s\n","계좌번호","계좌잔액");
			for(int i = 0 ; i < account.length; i++) {
				if(CheckIdAndAcc(id,account[i])) {
					data += account[i].accNumber + "\t"+account[i].money+"원\n";
				}
			}
			return data;
		}
	//새로운 계좌 추가
	boolean CheckAccount(String acc) {
		if(account == null) return false;
		for(int i = 0 ; i < account.length; i++) {
			if(account[i].accNumber.equals(acc)) {
				return true;
			}
		}
		return false;
	}
	//이체
	void TransferMoney(String id, String acc1,String acc2,int Money) {
		if(account == null) return;
		for(int i = 0 ; i < account.length; i++) {
			if(account[i].accNumber.equals(acc1) && account[i].clientId.equals(id)) {
				account[i].money -= Money;
			}else if(account[i].accNumber.equals(acc2)) {
				account[i].money += Money;
			}
		}
		System.out.println("[이체 완료]");
	}
	//아이디 와 계좌번호가 같은 것이 있는지 체크
	boolean CheckAccount(String acc,String id) {
		if(account == null) return false;
		for(int i = 0 ; i < account.length; i++) {
			if(CheckIdAndAcc(id, acc, account[i])) {
				return true;
			}
		}
		return false;
	}
	//아이디 값으로 한개의 계좌 삭제
	void DeleteOneAccount(String id, String acc) {
		if(account.length == 1) {
			account = null;
			System.out.println(acc+"계좌 삭제 완료");
			return;
		}
		Account[] copy = account;
		account = new Account[copy.length - 1];
		int idx = 0;
		for(int i = 0 ; i < copy.length; i++) {
			if(CheckIdAndAcc(id, acc, copy[i]) == false) {
				account[idx++] = copy[i];
			}
		}
		System.out.println(acc+"계좌 삭제 완료");
	}
	//새로운 계좌 생성
	void NewAccount(String Id, String accNumber) {
		if(account == null) {
			account = new Account[1];
		}else {
			Account[] copy = account;
			account = new Account[copy.length + 1];
			for(int i = 0 ; i < copy.length; i++) {
				account[i] = copy[i];
			}
		}
		account[account.length - 1] = new Account(Id,accNumber);
		System.out.println("계좌 추가 완료");
	}
	//계좌 잔액 출금 
	void OneUserSubMoney(int money,String id, String acc) {
		if(account == null )return;
		for(int i = 0 ; i < account.length; i++) {
			if(CheckIdAndAcc(id, acc, account[i])) {
				account[i].money -= money;
				break;
			}
		}
		System.out.println("[ 출금 완료 ]");
	}
	//아이다와 계좌번호가 같은지 확인 (반복문 용도)
	boolean CheckIdAndAcc(String id,String acc,Account account) {
		if(account.accNumber.equals(acc) && account.clientId.equals(id))
			return true;
		return false;
	}
	//아이디가 같은지 확인 (반복문)
	boolean CheckIdAndAcc(String id,Account account) {
		if(account.clientId.equals(id))
			return true;
		return false;
	}
	//유저 한명의 계좌 잔액 리턴
	int getOneUserMoney(String id, String acc) {
		if(account == null) return 0;
		for(int i = 0 ; i < account.length; i++) {
			if(CheckIdAndAcc(id, acc, account[i])) {
				return account[i].money;
			}
		}
		return 0;
	}
	//입금
	void PlustAccountMoney(int money, String id,String acc) {
		if(account == null) return;
		for(int i = 0 ; i < account.length; i++) {
			if(CheckIdAndAcc(id, acc, account[i])) {
				account[i].money += money;
				break;
			}
		}
		System.out.println("["+money+"입금 완료]");
	}
}
