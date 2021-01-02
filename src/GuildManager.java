import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

//������ ���
public class GuildManager {

	// �̱���
	private GuildManager() {
		initPlayer();
		initGuild();
		initUser();
	};

	private static GuildManager gm = new GuildManager();

	public static GuildManager getGm() {
		return gm;
	}

	Vector<Player> player = new Vector<>();
	Vector<Guild> guild = new Vector<>();
	User user = User.getUser();
	FileManager fm = FileManager.getFm();
	Random ran = new Random();
	Scanner scan = new Scanner(System.in);

	void initUser() {
		user.money += 100000;
		System.out.println("======================================================");
		System.out.println("[������] " + user.money + " ���");
	}

	void initPlayer() {
		String[] name = { "ȣ����", "������", "�罿", "�δ���", "����", "�ڹι�", "�䳢", "����", "��踻", "����", "�����", "Ļ�ŷ�" };
		int[] damage = { 10, 7, 3, 3, 9, 1, 2, 8, 12, 3, 4, 20 };
		int[] armor = { 2, 4, 7, 6, 1, 4, 6, 5, 4, 10, 11, 3 };
		int[] HP = { 100, 80, 50, 70, 200, 150, 120, 30, 100, 80, 77, 120 };
		boolean[] inParty = { true, true, true, true, false, false, false, false, false, false, false, false };
		for (int i = 0; i < name.length; i++) {
			player.add(new Player((i + 1), name[i], damage[i], armor[i], HP[i], inParty[i]));
		}
	}

	void initGuild() {// player �� 5������ �������� ����
		for (int i = 0; i < 5; i++) {
			guild.add(new Guild(player.get(i)));
		}
	}

	int checkNo(int no, int N) {
		int idx = -1;
		if (N == 0) {// player���� ����
			for (int i = 0; i < player.size(); i++) {
				if (no == player.get(i).no) {
					idx = i;
				}
			}
		} else {// guild���� ����
			for (int i = 0; i < guild.size(); i++) {
				if (no == guild.get(i).no) {
					idx = i;
				}
			}
		}
		return idx;
	}

	// Show guild member
	void guildList() {
		System.out.println("======================= [����] ======================");
		int guildCount = guild.size();
		for (int i = 0; i < guildCount; i++) {
			guild.get(i).showGuild();
		}
	}

	// player->guild
	void addToGuild() {
		System.out.println("======================= [���� ����] ======================");
		System.out.println("[�޼���] ������ �������� �����˴ϴ�. [���� : 1000 ��� ]");
		if (user.money < 1000) {
			System.out.println("[�޼���] �������� �����մϴ�.");
			return;
		}
		user.money -= 1000;
		if (player.size() > guild.size()) {
			System.out.println(".");
			System.out.println(".");
			System.out.println(".");
			int rNum = ran.nextInt(player.size()) + 1;
			// showPlayer
			// ���Ե� ���� �Է�
			int idx = 0;
			for (int i = 0; i < player.size(); i++) {
				if (player.get(i).no == rNum) {
					player.get(i).showPlayer();
					idx = i;
				}
			}
			System.out.println();
			// �ߺ��� ��, 1200��� ����
			for (int i = 0; i < guild.size(); i++) {
				if (guild.get(i).no == rNum) {// ��忡 �̹� ���� ��,
					System.out.println("[�޼���] ������ �ߺ��ǹǷ�, 1200��带 �����մϴ�.");
					user.money += 1200;
					System.out.println("���� ������ : " + user.money + "���");
					return;
				}
			}
			guild.add(new Guild(player.get(idx)));
			System.out.println("[�޼���] ������ �����մϴ�.");

		} else {
			System.out.println("[�޼���] ��� �ο��� ���� á���ϴ�.");
		}
	}

	// guild->player
	void delFromGuild() {// ���� ����
		System.out.println("======================= [���� ����] ======================");
		System.out.println("[Lv.1] : 300 ��� , [Lv.2] : 500��� [Lv.3] 750��� [Lv.4] 900��� [Lv.5] 1100���");
		System.out.println();
		guildList();
		System.out.println("[�޼���] ������ ������ No�� �Է����ּ��� : ");
		int choice = scan.nextInt();
		int idx = -1;
		for (int i = 0; i < guild.size(); i++) {
			if (choice == guild.get(i).no) {
				if (guild.get(i).inParty == true) {
					System.out.println("[�޼���] " + guild.get(i).name + "��(��) ���� ��Ƽ�� �Ҽ����ֽ��ϴ�. ��Ƽ���� ���� ��ü�ϼ���");
					return;
				}
				idx = i;
			}

		}
		if (idx == -1) {
			System.out.println("[�޼���] No�� �߸� �Է��ϼ̽��ϴ�.");
			return;
		}

		// ���� �� ��� ����
		if (guild.get(idx).level == 1) {
			user.money += 300;
		} else if (guild.get(idx).level == 2) {
			user.money += 600;
		} else if (guild.get(idx).level == 3) {
			user.money += 750;
		} else if (guild.get(idx).level == 4) {
			user.money += 900;
		} else if (guild.get(idx).level == 5) {
			user.money += 1100;
		}
		System.out.println("[�޼���] " + guild.get(idx).name + "�� �����մϴ�.");
		System.out.println("���� ������ : " + user.money + "���");
		guild.remove(idx);
	}

	// party<->guild
	void switchMember() {
		guildList();
		System.out.println("��ü�� No�� �Է��ϼ��� ");
		int outN = scan.nextInt();
		int idx = checkNo(outN, 1);// 0�� player, 1�� guild

		// ����ó��
		if (idx == -1) {
			System.out.println("[�޼���] �ش��ϴ� No�� �����ϴ�.");
			return;
		}
		// ���� ��Ƽ���� ������������
		if (guild.get(idx).inParty == false) {
			System.out.println("�ش� ĳ���ʹ� ���� ��Ƽ���� ���������ʽ��ϴ�. ��Ƽ���� �����ִ� ĳ���͸� �������ּ���");
			return;
		}

		System.out.println("������ No�� �Է��ϼ��� ");
		int inN = scan.nextInt();
		int idx2 = checkNo(inN, 1);

		// ����ó��
		if (idx2 == -1) {
			System.out.println("[�޼���] �ش��ϴ� No�� �����ϴ�.");
			return;
		}
		// ���� ��Ƽ���� ��������()
		if (guild.get(idx2).inParty == true) {
			System.out.println("�ش� ĳ���ʹ� ���� ��Ƽ���� �����ֽ��ϴ�. ��Ƽ���� ���������� ĳ���͸� �������ּ���");
			return;
		}

		guild.get(idx).inParty = false;
		guild.get(idx2).inParty = true;
		System.out.println("[" + guild.get(idx).name + "]���� [" + guild.get(idx2).name + "] �� ��Ƽ���� ����Ǿ����ϴ�.");
	}

	void arrangeMember() {
		for (int i = 0; i < guild.size(); i++) {
			for (int j = 0; j < i; j++) {
				if (guild.get(i).no < guild.get(j).no) {
					Guild temp = new Guild();
					temp = guild.get(i);
					guild.set(i, guild.get(j));
					guild.set(j, temp);
				}
			}
		}
		System.out.println("[�޼���] ������ �Ϸ�Ǿ����ϴ�.");
	}

	// �������� ����
	void equipItem() {

		guildList();
		System.out.println("�������� ������ ������ �����ϼ���");
		int mem = scan.nextInt();
		guild.get(mem).showGuildItem();

	}

	// inventory->sell
	void resellItem() {
	}

}
