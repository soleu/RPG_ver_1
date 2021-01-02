import java.util.Scanner;

public class UserManager {
	// 싱글톤
	private UserManager() {
	};

	private static UserManager um = new UserManager();

	public static UserManager getUserManger() {
		return um;
	}

	Scanner scan = new Scanner(System.in);
	User user = User.getUser();
	Shop shop = Shop.getShop();
	GuildManager gm = GuildManager.getGm();

	void equipInven() {
		System.out.println("======================= [아이템리스트] ======================");
		for (int j = 0; j < user.invenCount; j++) {
			String name = shop.item.get(user.inven[j][0])[user.inven[j][1]].name;
			int stat = shop.item.get(user.inven[j][0])[user.inven[j][1]].stat;
			String equip;
			if (user.equipNo[j] == 0) {
				equip = "없음";
			} else {
				equip = Integer.toString(user.equipNo[j]);
			}
			System.out.println("[" + (j + 1) + "번][이름 : " + name + "][능력 : " + stat + "][착용 : No." + equip + "]");
		}
		System.out.println("번호를 입력하세요 : ");
		int num = scan.nextInt();
		num--;
		if (user.equipNo[num] != 0) {
			System.out.println("해당 아이템은 이미 착용 중입니다. 다른 아이템을 선택해주세요");
			return;
		}

		gm.guildList();
		System.out.println("사용할 길드원의 NO를 선택하세요 : ");
		int mem = scan.nextInt();
		int noIdx = 0;
		for (int i = 0; i < gm.guild.size(); i++) {
			if (mem == gm.guild.get(i).no) {
				noIdx = i;
			}
		}

		int cateN = user.inven[num][0];
		int itemN = user.inven[num][1];

		// 물약은 소모품으로 사용.
		if (shop.item.get(cateN)[0].name.equals("물약")) {
			if (shop.item.get(cateN)[itemN].name.equals("HP 물약")) {
				gm.guild.get(noIdx).HPCharge(shop.item.get(cateN)[itemN].stat);
			} else if (shop.item.get(cateN)[itemN].name.equals("레벨업 물약")) {
				gm.guild.get(noIdx).levelBonus(shop.item.get(cateN)[itemN].stat);
			}

			// inven에서 삭제
			for (int i = num; i < user.invenCount - 1; i++) {
				user.inven[i][0] = user.inven[i + 1][0];
				user.inven[i][1] = user.inven[i + 1][1];
			}
			user.invenCount--;
		}
		// 다른 아이템은 캐릭터에게 장비
		else if (shop.item.get(cateN)[0].name.equals("무기")) {
			gm.guild.get(noIdx).weaponBonus(shop.item.get(cateN)[itemN].name, shop.item.get(cateN)[itemN].stat);
			user.equipNo[num] = gm.guild.get(noIdx).no;
		}

		else if (shop.item.get(cateN)[0].name.equals("갑옷")) {
			gm.guild.get(noIdx).armorBonus(shop.item.get(cateN)[itemN].name, shop.item.get(cateN)[itemN].stat);
			user.equipNo[num] = gm.guild.get(noIdx).no;
		}

		else if (shop.item.get(cateN)[0].name.equals("반지")) {
			gm.guild.get(noIdx).ringBonus(shop.item.get(cateN)[itemN].name, shop.item.get(cateN)[itemN].stat);
			user.equipNo[num] = gm.guild.get(noIdx).no;
		}

		// 변화된 state 출력
		gm.guild.get(noIdx).showGuildItem();

	}

	void resellInven() {
		System.out.println("[판매] [구입 금액 *0.5]");
		System.out.println("======================= [아이템리스트] ======================");
		for (int j = 0; j < user.invenCount; j++) {
			String name = shop.item.get(user.inven[j][0])[user.inven[j][1]].name;
			int stat = shop.item.get(user.inven[j][0])[user.inven[j][1]].stat;
			int price = shop.item.get(user.inven[j][0])[user.inven[j][1]].price;
			String equip;
			if (user.equipNo[j] == 0) {
				equip = "없음";
			} else {
				equip = Integer.toString(user.equipNo[j]);
			}
			System.out.println("[" + (j + 1) + "번][이름 : " + name + "][능력 : " + stat + "][착용 : No." + equip + "][가격 : "
					+ price + "]");
		}
		System.out.println("번호를 입력하세요 : ");
		int num = scan.nextInt();
		num--;
		if (user.equipNo[num] != 0) {
			System.out.println("해당 아이템은 착용 중이므로 착용을 먼저 해제하세요.");
			return;
		}

		int cateN = user.inven[num][0];
		int itemN = user.inven[num][1];
		System.out.println("해당 아이템을 판매합니다.+" + shop.item.get(cateN)[itemN].price * 0.5 + " 골드");

		// 소지금 빼기
		user.money += shop.item.get(cateN)[itemN].price * 0.5;
		System.out.println("[소지금 : " + user.money + "골드]");

		// inven에서 삭제
		for (int i = num; i < user.invenCount - 1; i++) {
			user.inven[i][0] = user.inven[i + 1][0];
			user.inven[i][1] = user.inven[i + 1][1];
		}
		user.invenCount--;
	}

}
