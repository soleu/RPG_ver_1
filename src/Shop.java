import java.util.Scanner;
import java.util.Vector;

//���� �Ǹ� ���� ���
public class Shop {
	// �̱���
	private Shop() {
		initCate();
		initItem();

	};

	private static Shop shop = new Shop();

	public static Shop getShop() {
		return shop;
	}

	User user = User.getUser();
	Scanner scan = new Scanner(System.in);
	Vector<Item[]> item;

	String[] cateList = { "����", "����", "����", "����" };

	boolean check(String cate) {
		int cateCount = item.size();
		for (int i = 0; i < cateCount; i++) {
			if (cate.equals(item.get(i)[0].name)) {
				return true;
			}
		}
		return false;
	}

	void initCate() {
		item = new Vector<>();

		Item[] temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "����";
		item.add(temp);

		temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "����";
		item.add(temp);

		temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "����";
		item.add(temp);

		temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "����";
		item.add(temp);
	}

	void initItem() {

		String[] name = { "������", "ö��", "�����Ǿ�", "���װ���", "û������", "��ö�Ǳݰ���", "��������", "������ ����", "HP ����", "������ ����" };
		String[] cate = { "����", "����", "����", "����", "����", "����", "����", "����", "����", "����" };
		// ���� : ���ݷ� ����, ���� : ���� ����, ���� : HP ä��, ���� : �뵵�� ���
		int[] stat = { 3, 5, 10, 10, 20, 30, 5, 10, 30, 1 };
		int[] price = { 2000, 3000, 6000, 2000, 4000, 8000, 2000, 5000, 1000, 20000 };

		for (int i = 0; i < name.length; i++) {
			for (int j = 0; j < 4; j++) {
				if (item.get(j)[0].name.equals(cate[i])) {
					int itemCount = item.get(j).length;
					Item[] temp = new Item[itemCount + 1];
					for (int k = 0; k < temp.length; k++) {
						temp[k] = new Item();
					}
					for (int k = 0; k < itemCount; k++) {
						temp[k].name = item.get(j)[k].name;
						temp[k].price = item.get(j)[k].price;
						temp[k].stat = item.get(j)[k].stat;
					}
					temp[itemCount].name = name[i];
					temp[itemCount].price = price[i];
					temp[itemCount].stat = stat[i];
					item.set(j, temp);
				}
			}
		}
	}

	void buyItem(int choice) {
		if (choice == 1) {
			System.out.println("======================= [����] ======================");
			System.out.println("[����]�� ���ݷ��� ������ŵ�ϴ�.");
		} else if (choice == 2) {
			System.out.println("======================= [��] ======================");
			System.out.println("[����]�� ������ ������ŵ�ϴ�.");
		} else if (choice == 3) {
			System.out.println("======================= [����] ======================");
			System.out.println("[����]�� �⺻ ü���� ������ŵ�ϴ�.");
		} else if (choice == 4) {
			System.out.println("======================= [����] ======================");
			System.out.println("[����]�� ü���� �����ϰų�, �������� �ϰ��մϴ�.");
		}

		choice--;
		int itemCount = item.get(choice).length;
		for (int i = 1; i < itemCount; i++) {
			System.out.println("[" + i + "��][�̸� : " + item.get(choice)[i].name + "][�ɷ� : " + item.get(choice)[i].stat
					+ "][���� : " + item.get(choice)[i].price + "]");
		}

		System.out.println();
		System.out.println("[��� : " + user.money + " ]");
		System.out.println("������ ������ ��ȣ�� �Է��ϼ��� [0. �ڷΰ���]");
		int choice2 = scan.nextInt();
		if (choice2 == 0) {
			return;
		}
		if (user.money < item.get(choice)[choice2].price) {
			System.out.println("�������� �����մϴ�.");
			return;
		}

		System.out
				.println("[�̸� : " + item.get(choice)[choice2].name + "][���� : " + item.get(choice)[choice2].price + "]");
		System.out.println("[�޼���] �������� ���������� �����߽��ϴ�.");
		user.money -= item.get(choice)[choice2].price;
		System.out.println("[��� : " + user.money + "]");
		user.shopping(choice, choice2);
	}
}
