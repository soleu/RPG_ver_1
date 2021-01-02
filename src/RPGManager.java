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
		System.out.println("======================= [메인 메뉴] ======================");
		System.out.println("[1.길드 관리] [2.상점] [3.인벤토리]");
		System.out.println("[4.저장] [5.로드] [0.종료]");
		System.out.println();
	}

	void guildMenu() {
		System.out.println("======================= [길드 관리] ======================");
		System.out.println("[1.길드 목록] [2.길드원 영입] [3.길드원 퇴출]");
		System.out.println("[4.파티원 교체] [5.정렬] [0.종료]");
	}

	void shopMenu() {
		System.out.println("======================= [상점 메뉴] ======================");
		System.out.println("[1.무기] [2.갑옷] [3.반지] [4.물약] [0.뒤로가기]");
	}

	void invenMenu() {
		System.out.println("======================= [인벤토리 메뉴] ======================");
		System.out.println("[1.착용] [2.판매] [0.뒤로가기]");
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
				System.out.println("게임을 종료합니다.");
				break;
			}
		}
	}
}
