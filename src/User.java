import java.util.Scanner;

//������� �κ��丮,������ ����
public class User {
	Scanner scan = new Scanner(System.in);

	// �̱���
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
	int equipNo[] = new int[100];// equipNo(0�� ������)

	// ������ �κ��丮�� ����
	void shopping(int cateN, int itemN) {
		inven[invenCount][0] = cateN;
		inven[invenCount][1] = itemN;
		invenCount++;
	}

}

//�κ��丮
//������
