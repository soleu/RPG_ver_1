import java.util.Scanner;
import java.util.Vector;

//상점 판매 구입 기능
public class Shop {
	// 싱글톤
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

	String[] cateList = { "무기", "갑옷", "반지", "물약" };

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
		temp[0].name = "무기";
		item.add(temp);

		temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "갑옷";
		item.add(temp);

		temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "반지";
		item.add(temp);

		temp = new Item[1];
		temp[0] = new Item();
		temp[0].name = "물약";
		item.add(temp);
	}

	void initItem() {

		String[] name = { "나무검", "철검", "레이피어", "가죽갑옷", "청동갑옷", "강철판금갑옷", "나무반지", "도란의 반지", "HP 물약", "레벨업 물약" };
		String[] cate = { "무기", "무기", "무기", "갑옷", "갑옷", "갑옷", "반지", "반지", "물약", "물약" };
		// 무기 : 공격력 증진, 갑옷 : 방어력 증진, 반지 : HP 채움, 물약 : 용도별 기능
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
			System.out.println("======================= [무기] ======================");
			System.out.println("[무기]는 공격력을 증진시킵니다.");
		} else if (choice == 2) {
			System.out.println("======================= [방어구] ======================");
			System.out.println("[갑옷]은 방어력을 증진시킵니다.");
		} else if (choice == 3) {
			System.out.println("======================= [반지] ======================");
			System.out.println("[반지]는 기본 체력을 증진시킵니다.");
		} else if (choice == 4) {
			System.out.println("======================= [물약] ======================");
			System.out.println("[물약]은 체력을 충전하거나, 레벨업을 하게합니다.");
		}

		choice--;
		int itemCount = item.get(choice).length;
		for (int i = 1; i < itemCount; i++) {
			System.out.println("[" + i + "번][이름 : " + item.get(choice)[i].name + "][능력 : " + item.get(choice)[i].stat
					+ "][가격 : " + item.get(choice)[i].price + "]");
		}

		System.out.println();
		System.out.println("[골드 : " + user.money + " ]");
		System.out.println("구입할 아이템 번호를 입력하세요 [0. 뒤로가기]");
		int choice2 = scan.nextInt();
		if (choice2 == 0) {
			return;
		}
		if (user.money < item.get(choice)[choice2].price) {
			System.out.println("소지금이 부족합니다.");
			return;
		}

		System.out
				.println("[이름 : " + item.get(choice)[choice2].name + "][가격 : " + item.get(choice)[choice2].price + "]");
		System.out.println("[메세지] 아이템을 성공적으로 구매했습니다.");
		user.money -= item.get(choice)[choice2].price;
		System.out.println("[골드 : " + user.money + "]");
		user.shopping(choice, choice2);
	}
}
