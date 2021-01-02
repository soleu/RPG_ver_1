
//상점 기능 관리
public class Item {
	String name;// 이름
	int stat;// 능력
	int price;// 가격

	Item() {
		name = "";
		stat = 0;
		price = 0;
	}

	Item(String cate) {// 배열 맨 첫번째는 카테고리 분류로 사용
		this.name = cate;
	}

	Item(String name, int stat, int price) {
		this.name = name;
		this.stat = stat;
		this.price = price;
	}

}
