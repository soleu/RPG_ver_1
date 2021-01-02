public class Guild {
	int no;// 고유번호
	String name;// 이름
	int level;// 레벨
	int HP;// 체력 최대값
	int ATK;// 공격력
	int DEF;// 방어력
	boolean inParty;// 파티에 참여중인지
	String weapon;// 무기착용
	String armor;// 갑옷착용
	String ring;// 반지착용

	Guild() {

	}

	// 초반 생성시, 길드원 생성
	Guild(Player player) {
		this.no = player.no;
		this.name = player.name;
		this.DEF = player.DEF;
		this.ATK = player.ATK;
		this.inParty = player.inParty;
		this.level = player.level;
		this.HP = player.HP;
		this.weapon = "없음";
		this.armor = "없음";
		this.ring = "없음";
	}

	Guild(int no, String name, int level, int HP, int ATK, int DEF, boolean inParty, String weapon, String armor,
			String ring) {
		this.no = no;
		this.name = name;
		this.DEF = DEF;
		this.ATK = ATK;
		this.inParty = inParty;
		this.level = level;
		this.HP = HP;
		this.weapon = weapon;
		this.armor = weapon;
		this.ring = weapon;
	}

	void HPCharge(int HP) {
		System.out.println("체력이 이미 가득 차 있습니다.");
	}

	void levelBonus(int Lv) {
		System.out.println(Lv);
		this.level += Lv;
	}

	void weaponBonus(String weapon, int ATK) {
		this.weapon = weapon;
		this.ATK += ATK;
	}

	void armorBonus(String armor, int DEF) {
		this.armor = armor;
		this.DEF += DEF;
	}

	void ringBonus(String ring, int HP) {
		this.ring = ring;
		this.HP += HP;
	}

	void showGuild() {
		System.out.println("======================================================");
		System.out.println("[No." + no + "] [이름 : " + name + "] [레벨 : " + level + "] [ HP: " + HP + " / " + HP + "]");
		System.out.println("[공격력 : " + ATK + "] [방어력 : " + DEF + "] [파티중 : " + inParty + "]");
		System.out.println();
	}

	void showGuildItem() {
		System.out.println("======================================================");
		System.out.println("[No." + no + "] [이름 : " + name + "] [레벨 : " + level + "] [ HP: " + HP + " / " + HP + "]");
		System.out.println("[공격력 : " + ATK + "] [방어력 : " + DEF + "] [파티중 : " + inParty + "]");
		System.out.println("[무기 : " + weapon + "]");
		System.out.println("[방어구 : " + armor + "]");
		System.out.println("[반지 : " + ring + "]");
	}

}
