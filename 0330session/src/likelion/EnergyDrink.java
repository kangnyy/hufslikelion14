package likelion;

public class EnergyDrink extends Beverage {

    private int taurine; // EnergyDrink만의 추가 필드

    public EnergyDrink(String name, int price,
                       int stock, int taurine) {
        super(name, price, stock);
        this.taurine = taurine;
    }

    @Override
    public void displayInfo() {
        System.out.println("이름: "   + getName());    // getter 사용
        System.out.println("가격: "   + getPrice() + "원");
        System.out.println("재고: "   + getStock() + "개");
        System.out.println("타우린: " + taurine + "mg");
    }
}