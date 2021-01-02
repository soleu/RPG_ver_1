import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

//길드관리 기능
public class GuildManager {

	// 싱글톤
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
		System.out.println("[소지금] " + user.money + " 골드");
	}

	void initPlayer() {
		String[] name = { "호랑이", "강아지", "사슴", "두더지", "사자", "박민민", "토끼", "고릴라", "얼룩말", "여우", "고양이", "캥거루" };
		int[] damage = { 10, 7, 3, 3, 9, 1, 2, 8, 12, 3, 4, 20 };
		int[] armor = { 2, 4, 7, 6, 1, 4, 6, 5, 4, 10, 11, 3 };
		int[] HP = { 100, 80, 50, 70, 200, 150, 120, 30, 100, 80, 77, 120 };
		boolean[] inParty = { true, true, true, true, false, false, false, false, false, false, false, false };
		for (int i = 0; i < name.length; i++) {
			player.add(new Player((i + 1), name[i], damage[i], armor[i], HP[i], inParty[i]));
		}
	}

	void initGuild() {// player 중 5번까지 길드원으로 지정
		for (int i = 0; i < 5; i++) {
			guild.add(new Guild(player.get(i)));
		}
	}

	int checkNo(int no, int N) {
		int idx = -1;
		if (N == 0) {// player에서 리턴
			for (int i = 0; i < player.size(); i++) {
				if (no == player.get(i).no) {
					idx = i;
				}
			}
		} else {// guild에서 리턴
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
		System.out.println("======================= [길드원] ======================");
		int guildCount = guild.size();
		for (int i = 0; i < guildCount; i++) {
			guild.get(i).showGuild();
		}
	}

	// player->guild
	void addToGuild() {
		System.out.println("======================= [길드원 영입] ======================");
		System.out.println("[메세지] 길드원은 랜덤으로 지정됩니다. [영입 : 1000 골드 ]");
		if (user.money < 1000) {
			System.out.println("[메세지] 소지금이 부족합니다.");
			return;
		}
		user.money -= 1000;
		if (player.size() > guild.size()) {
			System.out.println(".");
			System.out.println(".");
			System.out.println(".");
			int rNum = ran.nextInt(player.size()) + 1;
			// showPlayer
			// 영입될 길드원 입력
			int idx = 0;
			for (int i = 0; i < player.size(); i++) {
				if (player.get(i).no == rNum) {
					player.get(i).showPlayer();
					idx = i;
				}
			}
			System.out.println();
			// 중복될 시, 1200골드 지급
			for (int i = 0; i < guild.size(); i++) {
				if (guild.get(i).no == rNum) {// 길드에 이미 있을 때,
					System.out.println("[메세지] 길드원이 중복되므로, 1200골드를 지급합니다.");
					user.money += 1200;
					System.out.println("현재 소지금 : " + user.money + "골드");
					return;
				}
			}
			guild.add(new Guild(player.get(idx)));
			System.out.println("[메세지] 길드원을 영입합니다.");

		} else {
			System.out.println("[메세지] 길드 인원이 가득 찼습니다.");
		}
	}

	// guild->player
	void delFromGuild() {// 길드원 퇴출
		System.out.println("======================= [길드원 퇴출] ======================");
		System.out.println("[Lv.1] : 300 골드 , [Lv.2] : 500골드 [Lv.3] 750골드 [Lv.4] 900골드 [Lv.5] 1100골드");
		System.out.println();
		guildList();
		System.out.println("[메세지] 퇴출할 길드원의 No를 입력해주세요 : ");
		int choice = scan.nextInt();
		int idx = -1;
		for (int i = 0; i < guild.size(); i++) {
			if (choice == guild.get(i).no) {
				if (guild.get(i).inParty == true) {
					System.out.println("[메세지] " + guild.get(i).name + "은(는) 현재 파티에 소속해있습니다. 파티원을 먼저 교체하세요");
					return;
				}
				idx = i;
			}

		}
		if (idx == -1) {
			System.out.println("[메세지] No를 잘못 입력하셨습니다.");
			return;
		}

		// 레벨 당 골드 지급
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
		System.out.println("[메세지] " + guild.get(idx).name + "을 퇴출합니다.");
		System.out.println("현재 소지금 : " + user.money + "골드");
		guild.remove(idx);
	}

	// party<->guild
	void switchMember() {
		guildList();
		System.out.println("교체할 No를 입력하세요 ");
		int outN = scan.nextInt();
		int idx = checkNo(outN, 1);// 0은 player, 1은 guild

		// 예외처리
		if (idx == -1) {
			System.out.println("[메세지] 해당하는 No가 없습니다.");
			return;
		}
		// 현재 파티원에 속해있지않음
		if (guild.get(idx).inParty == false) {
			System.out.println("해당 캐릭터는 현재 파티원에 속해있지않습니다. 파티원에 속해있는 캐릭터를 선택해주세요");
			return;
		}

		System.out.println("참가할 No를 입력하세요 ");
		int inN = scan.nextInt();
		int idx2 = checkNo(inN, 1);

		// 예외처리
		if (idx2 == -1) {
			System.out.println("[메세지] 해당하는 No가 없습니다.");
			return;
		}
		// 현재 파티원에 속해있음()
		if (guild.get(idx2).inParty == true) {
			System.out.println("해당 캐릭터는 현재 파티원에 속해있습니다. 파티원에 속하지않은 캐릭터를 선택해주세요");
			return;
		}

		guild.get(idx).inParty = false;
		guild.get(idx2).inParty = true;
		System.out.println("[" + guild.get(idx).name + "]에서 [" + guild.get(idx2).name + "] 로 파티원이 변경되었습니다.");
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
		System.out.println("[메세지] 정렬이 완료되었습니다.");
	}

	// 길드원에게 착용
	void equipItem() {

		guildList();
		System.out.println("아이템을 착용할 길드원을 선택하세요");
		int mem = scan.nextInt();
		guild.get(mem).showGuildItem();

	}

	// inventory->sell
	void resellItem() {
	}

}
