
//���� ��� ����
public class Item {
	String name;// �̸�
	int stat;// �ɷ�
	int price;// ����

	Item() {
		name = "";
		stat = 0;
		price = 0;
	}

	Item(String cate) {// �迭 �� ù��°�� ī�װ� �з��� ���
		this.name = cate;
	}

	Item(String name, int stat, int price) {
		this.name = name;
		this.stat = stat;
		this.price = price;
	}

}
