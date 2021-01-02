//길드원을 포함한 모든 캐릭터
public class Player {
	int no;// 고유번호
	String name;// 이름
	int level;// 레벨
	int HP;// 체력 최대값
	int ATK;// 공격력
	int DEF;// 방어력
	boolean inParty;// 파티에 참여중인지
	int weapon;// 무기착용
	int armor;// 갑옷착용
	int ring;// 반지착용

	Player(int no, String name, int ATK, int DEF, int HP, boolean inParty) {
		this.no = no;
		this.name = name;
		this.ATK = ATK;
		this.DEF = DEF;
		this.HP = HP;
		this.level = 1;
		this.inParty = inParty;
		this.weapon = 0;
		this.armor = 0;
		this.ring = 0;

	}// 초기 플레이어 생성

	void showPlayer() {
		System.out.println("[No." + no + "] [이름 : " + name + "] [레벨 : " + level + "] [ HP: " + HP + " / " + HP + "]");
		System.out.println("[공격력 : " + ATK + "] [방어력 : " + DEF + "] [파티중 : " + inParty + "]");
		System.out.println();
	}

}
