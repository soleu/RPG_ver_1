import java.util.Scanner;

public class RPGManager {
	// Singleton
	private RPGManager() {
	};

	private static RPGManager rpg = new RPGManager();

	public static RPGManager getRPGManger() {
		return rpg;
	}

	GuildManager gm = GuildManager.getGm();
	FileManager fm = FileManager.getFm();
	UserManager um = UserManager.getUserManger();
	Shop shop = Shop.getShop();
	Scanner scan = new Scanner(System.in);

	void MainMenu() {
		System.out.println("======================= [���� �޴�] ======================");
		System.out.println("[1.��� ����] [2.����] [3.�κ��丮]");
		System.out.println("[4.����] [5.�ε�] [0.����]");
		System.out.println();
	}

	void guildMenu() {
		System.out.println("======================= [��� ����] ======================");
		System.out.println("[1.��� ���] [2.���� ����] [3.���� ����]");
		System.out.println("[4.��Ƽ�� ��ü] [5.����] [0.����]");
	}

	void shopMenu() {
		System.out.println("======================= [���� �޴�] ======================");
		System.out.println("[1.����] [2.����] [3.����] [4.����] [0.�ڷΰ���]");
	}

	void invenMenu() {
		System.out.println("======================= [�κ��丮 �޴�] ======================");
		System.out.println("[1.����] [2.�Ǹ�] [0.�ڷΰ���]");
	}

	void run() {
		while (true) {
			MainMenu();
			int choice = scan.nextInt();
			if (choice == 1) {
				while (true) {
					guildMenu();
					int choice2 = scan.nextInt();
					if (choice2 == 1) {
						gm.guildList();
					} else if (choice2 == 2) {
						gm.addToGuild();
					} else if (choice2 == 3) {
						gm.delFromGuild();
					} else if (choice2 == 4) {
						gm.switchMember();
					} else if (choice2 == 5) {
						gm.arrangeMember();
					} else if (choice2 == 0) {
						break;
					}
				} // gm
			} else if (choice == 2) {
				while (true) {
					shopMenu();
					int choice3 = scan.nextInt();
					if (choice3 == 0) {
						break;
					}
					shop.buyItem(choice3);
				}
			} // shop
			else if (choice == 3) {
				while (true) {
					invenMenu();
					int choice4 = scan.nextInt();
					if (choice4 == 1) {
						um.equipInven();
					} else if (choice4 == 2) {
						um.resellInven();
					} else if (choice4 == 0) {
						break;
					}
				}
			} // user
			else if (choice == 4) {
				fm.saveData(rpg);
			} // fm
			else if (choice == 5) {
				fm.loadData();
			} // fm
			else if (choice == 0) {
				System.out.println("������ �����մϴ�.");
				break;
			}
		}
	}
}
