package likelion;

public class IonDrink extends Beverage {

    private int electrolyte; // IonDrink만의 추가 필드

    public IonDrink(String name, int price,
                       int stock, int electrolyte) {
        super(name, price, stock);
        this.electrolyte = electrolyte;
    }

    @Override
    public void displayInfo() {
        System.out.println("이름: "   + getName());    // getter 사용
        System.out.println("가격: "   + getPrice() + "원");
        System.out.println("재고: "   + getStock() + "개");
        System.out.println("전해질: " + electrolyte + "mg");
    }
}