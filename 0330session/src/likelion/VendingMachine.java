package likelion;

public class VendingMachine {

    // ★ Beverage 타입 배열 = 모든 음료수를 담을 수 있습니다
    private Beverage[] slots;
    private int       count;

    public VendingMachine(int capacity) {
        slots = new Beverage[capacity];
        count = 0;
    }

    // 음료 추가: Beverage 타입이면 무엇이든 받습니다
    public void addBeverage(Beverage b) {
        slots[count] = b;
        count++;
    }

    // 메뉴 출력
    // ★ slots[i].displayInfo() → 실제 객체 타입의 메서드 실행 (다형성)
    public void showMenu() {
        System.out.println("=== 자판기 메뉴 ===");
        for (int i = 0; i < count; i++) {
            System.out.println("[" + (i + 1) + "]");
            slots[i].displayInfo(); // ← 다형성!
        }
    }

    // 구매: num은 메뉴 번호, money는 투입 금액
    public void purchase(int num, int money) {
        Beverage selected = slots[num - 1];

        if (money < selected.getPrice()) {
            System.out.println("금액이 부족합니다.");
            return;
        }

        boolean success = selected.decreaseStock();
        if (success) {
            int change = money - selected.getPrice();
            System.out.println(selected.getName() + " 구매 완료!");
            System.out.println("거스름돈: " + change + "원");
        }
    }
}