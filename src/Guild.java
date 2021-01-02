public class Guild {
	int no;// ������ȣ
	String name;// �̸�
	int level;// ����
	int HP;// ü�� �ִ밪
	int ATK;// ���ݷ�
	int DEF;// ����
	boolean inParty;// ��Ƽ�� ����������
	String weapon;// ��������
	String armor;// ��������
	String ring;// ��������

	Guild() {

	}

	// �ʹ� ������, ���� ����
	Guild(Player player) {
		this.no = player.no;
		this.name = player.name;
		this.DEF = player.DEF;
		this.ATK = player.ATK;
		this.inParty = player.inParty;
		this.level = player.level;
		this.HP = player.HP;
		this.weapon = "����";
		this.armor = "����";
		this.ring = "����";
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
		System.out.println("ü���� �̹� ���� �� �ֽ��ϴ�.");
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
		System.out.println("[No." + no + "] [�̸� : " + name + "] [���� : " + level + "] [ HP: " + HP + " / " + HP + "]");
		System.out.println("[���ݷ� : " + ATK + "] [���� : " + DEF + "] [��Ƽ�� : " + inParty + "]");
		System.out.println();
	}

	void showGuildItem() {
		System.out.println("======================================================");
		System.out.println("[No." + no + "] [�̸� : " + name + "] [���� : " + level + "] [ HP: " + HP + " / " + HP + "]");
		System.out.println("[���ݷ� : " + ATK + "] [���� : " + DEF + "] [��Ƽ�� : " + inParty + "]");
		System.out.println("[���� : " + weapon + "]");
		System.out.println("[�� : " + armor + "]");
		System.out.println("[���� : " + ring + "]");
	}

}
