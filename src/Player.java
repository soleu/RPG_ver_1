//������ ������ ��� ĳ����
public class Player {
	int no;// ������ȣ
	String name;// �̸�
	int level;// ����
	int HP;// ü�� �ִ밪
	int ATK;// ���ݷ�
	int DEF;// ����
	boolean inParty;// ��Ƽ�� ����������
	int weapon;// ��������
	int armor;// ��������
	int ring;// ��������

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

	}// �ʱ� �÷��̾� ����

	void showPlayer() {
		System.out.println("[No." + no + "] [�̸� : " + name + "] [���� : " + level + "] [ HP: " + HP + " / " + HP + "]");
		System.out.println("[���ݷ� : " + ATK + "] [���� : " + DEF + "] [��Ƽ�� : " + inParty + "]");
		System.out.println();
	}

}
