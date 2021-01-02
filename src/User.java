import java.util.Scanner;

//사용자의 인벤토리,소지금 관리
public class User {
	Scanner scan = new Scanner(System.in);

	// 싱글톤
	private User() {
		money = 100000;
		inven = new int[100][2];

	};

	private static User user = new User();

	public static User getUser() {
		return user;
	}

	int money;
	int[][] inven = new int[100][2];// cateN,itemN;
	int invenCount = 0;
	int equipNo[] = new int[100];// equipNo(0이 미착용)

	// 아이템 인벤토리에 저장
	void shopping(int cateN, int itemN) {
		inven[invenCount][0] = cateN;
		inven[invenCount][1] = itemN;
		invenCount++;
	}

}

//인벤토리
//소지금
