package view;

import java.util.List;
import java.util.Scanner;

import controller.AccountControl;
import controller.LogControl;
import model.vo.Account;
import model.vo.Log;

public class Menu {
	private Scanner sc = new Scanner(System.in);
	private AccountControl ac;
	private LogControl lc;
	
	public Menu() {
		ac = new AccountControl();
		lc = new LogControl(); 
	}
	
	public void mainMenu() {
		while(true) {
		System.out.print("=======================\n"
				+ "1. 계정 생성 \n"
				+ "2. 로그인 하기 \n"
				+ "3. 프로그램 종료 \n"
				+ "번호 선택 : ");
		int mnum = sc.nextInt();
		switch(mnum) {
		case 1 : ac.createUser(putData()); break;
		case 2 : Account acc = ac.logIn(putID(),putPwd());
					if(acc == null) {System.out.println("로그인 실패"); break;}
					if(Integer.parseInt(acc.getAccNumber()) > 1) {userMenu(acc); break;}
					else if(Integer.parseInt(acc.getAccNumber()) == 1) {managerMenu(acc); break;}
					else break;
		case 3 : return;
		}
	}
	}
	
	private void managerMenu(Account acc) {
		System.out.println("관리자 메뉴 접속...");
	}

	private void userMenu(Account acc) {
		System.out.println("\n사용자 메뉴 접속중...");
		while(true) {
			System.out.print("\n=================\n"
					+ "1. 내 계좌 조회 \n"
					+ "2. 입금하기 \n"
					+ "3. 출금하기 \n"
					+ "4. 송금하기 \n"
					+ "5. 거래내역 조회 \n"
					+ "6. 메인메뉴로 돌아가기 \n"
					+ "번호 선택 : ");
			int mnum = sc.nextInt();
			
			switch(mnum) {
			case 1 : printAcc(acc); break;
			case 2 : ac.deposit(acc, howMuch(2));  break;
			case 3 : ac.withdraw(acc, howMuch(3)); break;
			case 4 : Account rcc = putWho(); 
						if(sc.next().toLowerCase().charAt(0) == 'y') { ac.transfer(acc,rcc,howMuch(4)); break;}
						else break;
			case 5 : showDataMenu(acc); break;
			case 6 : return;
			}
		}
	}
	private void showDataMenu(Account acc) {
		System.out.print("===============\n"
				+ "1. 입금내역 조회\n"
				+ "2. 출금내역 조회\n"
				+ "번호 입력 : ");
		int mnum = sc.nextInt();
		switch(mnum) {
		case 1 : printDlog(lc.getDlog(acc)); 
		case 2 :
		}
	}
	private void printDlog(List<Log> list) {
		if(list.isEmpty())
			System.out.println("조회된 결과가 없습니다.");
		else
			for(Log e : list)
				System.out.println(e);
	}

	public Account putData() {
		System.out.print("이름 입력 : ");
		String name = sc.next();
		System.out.print("주민 등록 번호 입력 : ");
		String ssN = sc.next();
		System.out.print("핸드폰 번호 입력 ['-'생략] : ");
		String phone = sc.next();
		System.out.print("아이디 입력 : ");
		String id;
		while(ac.checkID(id = sc.next())) { // true이면 아이디가 중복이다.
			System.out.println("중복 된 아이디 입니다.");
			System.out.print("아이디 입력 : ");
		}
		System.out.println("사용할 수 있는 아이디 입니다.");
		String password;
		while(true) {
		System.out.print("비밀번호 입력 : ");
		password = sc.next();
		System.out.print("비밀번호 재입력 : ");
		if(password.equals(sc.next()))
			break;
		System.out.println("비밀번호가 일치하지 않습니다.");
		}
		
		return new Account(ssN, phone, name, id, password);
	}
	
	private int howMuch(int num) {
		switch(num) {
		case 2 : System.out.print("입금하실 금액을 입력하세요 : "); break;
		case 3 : System.out.print("출금하실 금액을 입력하세요 : "); break;
		case 4 : System.out.print("송금하실 금액을 입력하세요 : "); break;
		}
		return sc.nextInt();
	}
	private Account putWho() {
		System.out.print("송금 할 계좌번호 입력 : ");
		Account rcc = ac.checkRcc(sc.next());
		if(rcc.getAccNumber() == null) 
			System.out.println("입력하신 계좌번호는 없는 번호입니다.");
		else 
			System.out.print(rcc.getName()+"님에게 송금하시겠습니까? (y/n) :");
		return rcc;
	}
	
	
	private String putID() {
		System.out.print("아이디 입력 :");
		return sc.next();
	}
	private String putPwd() {
		System.out.print("패스워드 입력 : ");
		return sc.next();
	}
	private void printAcc(Account acc) {
		System.out.println("\n계좌번호 \t이름 \t잔액 \t개설날짜");
		System.out.println("==================================================");
		System.out.println(acc.getAccNumber()+"\t"+acc.getName()+"\t"+acc.getBal()+"원\t"+acc.getEstDate());
	}
	
}
