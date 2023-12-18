package Controller;

import java.util.regex.Pattern;

import DAO.AccountDAO;
import DAO.ClientDAO;
import Model.Util;

public class BankController {
	private AccountDAO Acdao;
	private ClientDAO cldao;
	private Util u;
	private String session;
	//사용 불가 이름 리스트
	private String noName[] = { "관리자", "사용자" };
	/*
	   [1] 관리자 [2] 사용자 [0] 종료
	   관리자
	   [1]회원목록 [2]회원 수정 [3]회원 삭제 [4]데이터 저장 [5]데이터 불러오기 [6]로그아웃
	   [2] 회원수정 : 회원 아이디 검색 비밀번호, 이름 수정 가능
	   [3] 회원삭제 : 회원아이디
	   [4] 데이터 저장 : account.txt ,client.txt

	   사용자
	   [1]회원가입 [2]로그인 [0]뒤로가기
	   [1]회원가입 : 아이디 중복

	   [1]계좌추가 [2]계좌삭제 [3]입금 [4]출금 [5]이체 [6]탈퇴 [7]마이페이지 [0]로그아웃
	   계좌 추가 : 숫자 4 - 4 - 4개 형태
	   계좌 삭제 : 본인계좌만 가능
	   입금 : 계좌번호 본인것만 가능 (100원이상 입금/출금/이체 가능)
	   이체 : 이체 하는 곳과 받는 곳만 다르면 가능
	   탈퇴 : 패스워드 다시 입력 받아서 탈퇴 가능
	   마이페이지 : 내계좌 목록 확인
	*/
	
	// 사용불가 아이디 체크
	private boolean CheckName(String id) {
		if (noName == null)
			return false;
		for (int i = 0; i < noName.length; i++)
			if (id.equals(noName[i]))
				return true;

		return false;
	}

	// 기본 설정
	public BankController() {
		// TODO Auto-generated constructor stub
		Acdao = new AccountDAO();
		cldao = new ClientDAO();
		session = "";
		u = new Util();
	}

	// 메뉴출력
	private void printMenu() {
		if (session.isBlank()) {
			System.out.print("[1] 관리자\n[2] 사용자\n[0] 종료\n[0-2]");
		} else if (session.equals("관리자")) {
			System.out.println("===[우리은행 관리자]===");
			System.out.print("[1]회원목록\n[2]회원 수정\n[3]회원 삭제\n[4]데이터 저장\n[5]데이터 불러오기\n[6]로그아웃\n[1-6]");
		} else if (session.equals("사용자")) {
			System.out.print("[1]회원가입\n[2]로그인\n[0]뒤로가기\n[0-2]");
		} else {
			System.out.println("===[우리은행 ATM]===");
			System.out.print("[1]계좌추가\n[2]계좌삭제\n[3]입금\n[4]출금\n[5]이체\n[6]탈퇴 \n[7]마이페이지\n[0]로그아웃\n[0-7]");
		}
	}

	// 메뉴 입력 범위 확인
	private boolean CheckInpNumber(int i) {
		if (session.isBlank()) {
			if (i < 0 || i > 2) {
				System.out.println("0 ~ 2 사이의 값을 입력해주세요");
				return true;
			}
		} else if (session.equals("관리자")) {
			if (i < 1 || i > 6) {
				System.out.println("1 ~ 6 사이의 값을 입력해주세요");
				return true;
			}
		} else if (session.equals("사용자")) {
			if (i < 0 || i > 2) {
				System.out.println("0 ~ 2 사이의 값을 입력해주세요");
				return true;
			}
		} else {
			if (i < 0 || i > 7) {
				System.out.println("0 ~ 7 사이의 값을 입력해주세요");
				return true;
			}
		}
		return false;
	}

	// 첫 화면 입력 처리
	private String NodataMenu(int i) {
		if (i == 1) {
			return "관리자";
		} else if (i == 2) {
			return "사용자";
		}
		return "";
	}

	// 관리자 메뉴 입력 처리
	private void ManagerMenu(int i) {
		if (i == 1) {
			// 회원목록 조회
			System.out.println("[회원목록]");
			System.out.println("============================");
			System.out.println(" No\tid\tpw\tname");
			System.out.println("----------------------------");
			String data = cldao.GetAllUser();
			data = data.replaceAll("/", "\t");
			System.out.println(data);
			System.out.println("============================");
		} else if (i == 2) {
			// 회원수정
			System.out.println("[회원수정]");
			String id = u.getString("►아이디 : ");
			if (!cldao.CheckId(id)) {
				System.err.println("존재하지 않는 아이디");
				return;
			}
			System.out.println("[1]비밀번호 변경");
			System.out.println("[2]이름 변경");
			int se = u.getInt("입력 :");
			if (se == 1) {
				String pw = u.getString("[회원수정]변경하실 비밀번호를 입력 하세요 :");
				cldao.ChangePw(id, pw);
			} else if (se == 2) {
				String name = u.getString("[회원수정]변경하실 이름을 입력 하세요 :");
				cldao.ChangeName(id, name);
			} else {
				System.out.println("잘못된 입력 입니다.");
			}
		} else if (i == 3) {
			// 회원 삭제
			System.out.println("[회원삭제]");
			String id = u.getString("►아이디 : ");
			if (!cldao.CheckId(id)) {
				System.err.println("존재하지 않는 아이디");
				return;
			}
		} else if (i == 4) {
			// 데이터 저장
			String data = cldao.GetAllUser();
			String data2 = Acdao.GetAllAccountData();
			if (data.isBlank()) {
				System.out.println("저장할 데이터가 없습니다.");
				return;
			}
			u.SaveData(data, data2);
		} else if (i == 5) {
			// 데이터 불러오기
			String data[] = u.RoadData();
			if (data[0] == null) {
				System.out.println("데이터가 없습니다!");
				return;
			}
			SetRodaData(data);
		}
	}

	// 유저 메뉴 입력 처리(로그인 안한상태)
	private void UserMenu(int i) {
		if (i == 1) {
			// 회원가입
			String id = u.getString("아이디를 입력 하세요 :");
			if (CheckName(id)) {
				System.out.println("사용 불가능 이름입니다.");
				return;
			}
			if (cldao.CheckId(id)) {
				System.out.println("중복된 아이디 입니다.");
				return;
			}
			String pw = u.getString("비밀번호를 입력 하세요 :");
			String name = u.getString("이름를 입력 하세요 :");
			cldao.NewClient(id, pw, name);
		} else if (i == 2) {
			// 로그인
			if (cldao.getClientLength() == -1) {
				System.out.println("데이터가 없습니다.");
				return;
			}
			String id = u.getString("아이디를 입력 하세요 :");
			String pw = u.getString("비밀번호를 입력 하세요 :");
			session = cldao.Login(id, pw);
			if (session.equals("사용자")) {
				System.out.println("아이디 또는 비밀번호를 확인하세요");
				return;
			}
			System.out.println(cldao.getName(session) + "님 환영합니다");
		}
	}

	// 로그인 한 유저 입력 처리
	private void LoginUserMenu(int i) {
		if (i == 1) {
			// 계좌 추가
			if (Acdao.UserAccountCount(session) >= 3) {
				System.out.println("계좌는 3개 까지만 생성 가능");
				return;
			}
			String accNumber = u.getString("계좌번호 :");
			if (CheckAccount(accNumber) == false) {
				System.err.println("1111-1111-1111 이와 같은 형태로 입력해주세요");
				return;
			}
			if (Acdao.CheckAccount(accNumber)) {
				System.out.println("중복된 계좌번호 입니다.");
				return;
			}
			Acdao.NewAccount(session, accNumber);
		} else if (i == 2) {
			// [2]계좌삭제
			CheckAccount(i, "삭제");
		} else if (i == 3) {
			// [3]입금
			CheckAccount(i, "입금");
		} else if (i == 4) {
			// [4]출금
			CheckAccount(i, "출금");
		} else if (i == 5) {
			// [5]이체
			CheckAccount(i, "이체");
		} else if (i == 6) {
			// [6]탈퇴
			String pw = u.getString("[탈퇴]비밀번호를 입력하세요 :");
			String copy = cldao.Login(session, pw);
			if (!copy.equals(session)) {
				System.out.println("비밀번호를 확인하세요!");
				return;
			}
			Acdao.DeleteUser(session);
			cldao.DeleteUser(session);
			session = "사용자";
		} else if (i == 7) {
			// [7]마이페이지
			System.out.println("=== [ 마이 페이지 ] ===");
			String data = Acdao.UserAllAccount(session);
			System.out.println(data);
		}
	}

	// 메뉴 호츌
	private boolean CallMenu(int i) {
		if (session.isBlank()) {
			if (i == 0) {
				// 종료시키기
				return false;
			} else {
				session = NodataMenu(i);
			}
		} else if (session.equals("관리자")) {
			if (i == 6) {
				// 뒤로가기
				session = "";
			} else {
				ManagerMenu(i);
			}
		} else if (session.equals("사용자")) {
			if (i == 0) {
				// 뒤로가기
				session = "";
			} else {
				UserMenu(i);
			}
		} else {
			if (i == 0) {
				// 로그아웃
				session = "";
			} else {
				LoginUserMenu(i);
			}

		}
		return true;
	}

	// 로드
	private void SetRodaData(String[] data) {
		cldao.SetData(data[0]);
		Acdao.SetData(data[1]);
	}

	// 계좌 삭제 -> 출금까지
	private void CheckAccount(int i, String s) {
		if (Acdao.UserAccountCount(session) == 0) {
			System.out.println("계좌가 없습니다!");
			return;
		}
		String acc = u.getString("[" + s + "]계좌번호를 입려하세요 : ");
		if (CheckAccount(acc) == false) {
			System.err.println("1111-1111-1111 이와 같은 형태로 입력해주세요");
			return;
		}
		if (Acdao.CheckAccount(acc, session) == false) {
			if (Acdao.CheckAccount(acc)) {
				System.out.println("본인 계좌만 가능합니다");
				return;
			}
			System.out.println("잘못된 계좌 번호 입니다.");
			return;
		}

		if (i == 2) {
			Acdao.DeleteOneAccount(session, acc);
		} else if (i == 3) {
			// 입금
			int money = u.getInt("입금하실 금액을 입력해주세요 :");
			if (money < 100) {
				System.out.println("100원 이상 입력해주세요");
				return;
			}
		} else if (i == 4) {
			// 출금
			int money = u.getInt("출금하실 금액을 입력해주세요 :");
			if (money > Acdao.getOneUserMoney(session, acc)) {
				System.out.println("계좌 잔액을 초과하여 입력하셨습니다.");
				return;
			}

		} else if (i == 5) {
			String acc2 = u.getString("[" + s + "]이체 받을 계좌번호를 입려하세요 : ");
			if (CheckAccount(acc2) == false) {
				System.err.println("1111-1111-1111 이와 같은 형태로 입력해주세요");
				return;
			} else if (acc.equals(acc2)) {
				System.out.println("같은 계좌로 이체 불가능");
				return;
			} else if (Acdao.CheckAccount(acc2) == false) {
				System.out.println("잘못된 계좌 번호 입니다.");
				return;
			}

			int money = u.getInt("이체하실 금액을 입력해주세요 :");
			if (money > Acdao.getOneUserMoney(session, acc)) {
				System.out.println("계좌 잔액을 초과하여 입력하셨습니다.");
				return;
			}
			Acdao.TransferMoney(session, acc, acc2, money);
		}

	}

	// 계좌번호 입력 형식 확인
	private boolean CheckAccount(String s) {
		//정규 표현식
		String pattern = "^[\\d]{4}-[\\d]{4}-[\\d]{4}$";
		return Pattern.matches(pattern, s);
	}

	// 메인에서 실행할 부분
	public void run() {
		while (true) {
			printMenu();
			int i = u.getInt("입력 : ");
			if (i == -1) {
				continue;
			}
			if (CheckInpNumber(i)) {
				continue;
			}
			if (CallMenu(i) == false) {
				System.out.println("프로그램 종료");
				break;
			}
			System.out.println("========================");
		}
	}

}
