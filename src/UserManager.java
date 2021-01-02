import java.util.Scanner;

public class UserManager {
	// �̱���
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
		System.out.println("======================= [�����۸���Ʈ] ======================");
		for (int j = 0; j < user.invenCount; j++) {
			String name = shop.item.get(user.inven[j][0])[user.inven[j][1]].name;
			int stat = shop.item.get(user.inven[j][0])[user.inven[j][1]].stat;
			String equip;
			if (user.equipNo[j] == 0) {
				equip = "����";
			} else {
				equip = Integer.toString(user.equipNo[j]);
			}
			System.out.println("[" + (j + 1) + "��][�̸� : " + name + "][�ɷ� : " + stat + "][���� : No." + equip + "]");
		}
		System.out.println("��ȣ�� �Է��ϼ��� : ");
		int num = scan.nextInt();
		num--;
		if (user.equipNo[num] != 0) {
			System.out.println("�ش� �������� �̹� ���� ���Դϴ�. �ٸ� �������� �������ּ���");
			return;
		}

		gm.guildList();
		System.out.println("����� ������ NO�� �����ϼ��� : ");
		int mem = scan.nextInt();
		int noIdx = 0;
		for (int i = 0; i < gm.guild.size(); i++) {
			if (mem == gm.guild.get(i).no) {
				noIdx = i;
			}
		}

		int cateN = user.inven[num][0];
		int itemN = user.inven[num][1];

		// ������ �Ҹ�ǰ���� ���.
		if (shop.item.get(cateN)[0].name.equals("����")) {
			if (shop.item.get(cateN)[itemN].name.equals("HP ����")) {
				gm.guild.get(noIdx).HPCharge(shop.item.get(cateN)[itemN].stat);
			} else if (shop.item.get(cateN)[itemN].name.equals("������ ����")) {
				gm.guild.get(noIdx).levelBonus(shop.item.get(cateN)[itemN].stat);
			}

			// inven���� ����
			for (int i = num; i < user.invenCount - 1; i++) {
				user.inven[i][0] = user.inven[i + 1][0];
				user.inven[i][1] = user.inven[i + 1][1];
			}
			user.invenCount--;
		}
		// �ٸ� �������� ĳ���Ϳ��� ���
		else if (shop.item.get(cateN)[0].name.equals("����")) {
			gm.guild.get(noIdx).weaponBonus(shop.item.get(cateN)[itemN].name, shop.item.get(cateN)[itemN].stat);
			user.equipNo[num] = gm.guild.get(noIdx).no;
		}

		else if (shop.item.get(cateN)[0].name.equals("����")) {
			gm.guild.get(noIdx).armorBonus(shop.item.get(cateN)[itemN].name, shop.item.get(cateN)[itemN].stat);
			user.equipNo[num] = gm.guild.get(noIdx).no;
		}

		else if (shop.item.get(cateN)[0].name.equals("����")) {
			gm.guild.get(noIdx).ringBonus(shop.item.get(cateN)[itemN].name, shop.item.get(cateN)[itemN].stat);
			user.equipNo[num] = gm.guild.get(noIdx).no;
		}

		// ��ȭ�� state ���
		gm.guild.get(noIdx).showGuildItem();

	}

	void resellInven() {
		System.out.println("[�Ǹ�] [���� �ݾ� *0.5]");
		System.out.println("======================= [�����۸���Ʈ] ======================");
		for (int j = 0; j < user.invenCount; j++) {
			String name = shop.item.get(user.inven[j][0])[user.inven[j][1]].name;
			int stat = shop.item.get(user.inven[j][0])[user.inven[j][1]].stat;
			int price = shop.item.get(user.inven[j][0])[user.inven[j][1]].price;
			String equip;
			if (user.equipNo[j] == 0) {
				equip = "����";
			} else {
				equip = Integer.toString(user.equipNo[j]);
			}
			System.out.println("[" + (j + 1) + "��][�̸� : " + name + "][�ɷ� : " + stat + "][���� : No." + equip + "][���� : "
					+ price + "]");
		}
		System.out.println("��ȣ�� �Է��ϼ��� : ");
		int num = scan.nextInt();
		num--;
		if (user.equipNo[num] != 0) {
			System.out.println("�ش� �������� ���� ���̹Ƿ� ������ ���� �����ϼ���.");
			return;
		}

		int cateN = user.inven[num][0];
		int itemN = user.inven[num][1];
		System.out.println("�ش� �������� �Ǹ��մϴ�.+" + shop.item.get(cateN)[itemN].price * 0.5 + " ���");

		// ������ ����
		user.money += shop.item.get(cateN)[itemN].price * 0.5;
		System.out.println("[������ : " + user.money + "���]");

		// inven���� ����
		for (int i = num; i < user.invenCount - 1; i++) {
			user.inven[i][0] = user.inven[i + 1][0];
			user.inven[i][1] = user.inven[i + 1][1];
		}
		user.invenCount--;
	}

}
