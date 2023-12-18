package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Util {
	private Scanner scanner = new Scanner(System.in);

	private File userf;
	private File accountf;
	private static Util instance;
	private Util() {
		userf = new File(".\\");
		accountf = new File(".\\");
	}
	
	public static Util GetInstance() {
		if (instance == null) {
			instance = new Util();
		}
		return instance;
	}
	
	// account.txt client.txt
	// 파일 있는 확인
	void CheckFile(File f, File f2) {
		try {
			if (f.exists() == false) {
				f.createNewFile();
			}
			if (f2.exists() == false) {
				f2.createNewFile();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	 * void tempData() { String userdata = "1001/test01/pw1/김철수\n"; userdata +=
	 * "1002/test02/pw2/이영희\n"; userdata += "1003/test03/pw3/신민수\n"; userdata +=
	 * "1004/test04/pw4/최상민";
	 * 
	 * String accountdata = "test01/1111-1111-1111/8000\n"; accountdata +=
	 * "test02/2222-2222-2222/5000\n"; accountdata +=
	 * "test01/3333-3333-3333/11000\n"; accountdata +=
	 * "test03/4444-4444-4444/9000\n"; accountdata +=
	 * "test01/5555-5555-5555/5400\n"; accountdata +=
	 * "test02/6666-6666-6666/1000\n"; accountdata +=
	 * "test03/7777-7777-7777/1000\n"; accountdata +=
	 * "test04/8888-8888-8888/1000\n"; }
	 */

	// 데이터 세이브
	public void SaveData(String user, String account) {

		CheckFile(userf, accountf);
		try (FileWriter ufw = new FileWriter(userf); FileWriter acfw = new FileWriter(accountf)) {
			ufw.write(user);
			acfw.write(account);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("저장 완료");
	}

	// 데이터 읽어와서 String배열로 리턴
	public String[] RoadData() {
		String data[] = new String[2];
		CheckFile(userf, accountf);
		try (BufferedReader ubr = new BufferedReader(new FileReader(userf));
				BufferedReader accbr = new BufferedReader(new FileReader(accountf))) {
			int i;
			while ((i = ubr.read()) != -1) {
				if (data[0] == null) {
					data[0] = "" + (char) i;
					continue;
				}
				data[0] += (char) i;
			}
			while ((i = accbr.read()) != -1) {
				if (data[1] == null) {
					data[1] = "" + (char) i;
					continue;
				}
				data[1] += (char) i;
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

	// String 입력 받기
	public String getString(String s) {
		String data = "";
		System.out.print(s);
		data = scanner.next();
		scanner.nextLine();
		return data;
	}

	// int값 입력받기
	public int getInt(String s) {
		int i;
		try {
			System.out.print(s);
			i = scanner.nextInt();
		} catch (Exception e) {
			System.out.println("숫자만 입력 가능");
			scanner.nextLine();
			return -1;
		}
		return i;
	}
}
