package likelion;

public class Beverage {

    // private: 클래스 밖에서 직접 접근 불가
    private String name;
    private int    price;
    private int    stock;


    public Beverage(String name, int price, int stock) {
        this.name  = name;
        this.price = price;
        this.stock = stock;
    }

    // getter: 값을 읽어주는 메서드
    public String getName()  { return name;  }
    public int    getPrice() { return price; }
    public int    getStock() { return stock; }

    // setter: 값을 저장하는 메서드 (잘못된 값을 막습니다)
    public void setStock(int stock) {
        if (stock < 0) {
            System.out.println("재고는 0 이상이어야 합니다.");
            return;
        }
        this.stock = stock;
    }

    // 재고를 1 줄이는 메서드 (판매 시 사용)
    public boolean decreaseStock() {
        if (stock == 0) {
            System.out.println(name + " 품절!");
            return false;
        }
        stock--;
        return true;
    }


    public void displayInfo() {
        System.out.println("이름: " + name);
        System.out.println("가격: " + price + "원");
        System.out.println("재고: " + stock + "개");
    }
}