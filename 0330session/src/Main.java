package likelion;

public class Main {
    public static void main(String[] args) {

        // 1. 자판기 생성
        VendingMachine vm = new VendingMachine(4);

        // 2. 음료 추가
        //    Beverage는 추상 클래스라 직접 만들 수 없습니다
        //    Coffee, EnergyDrink 같은 자식 클래스만 만들 수 있습니다
        vm.addBeverage(new Coffee      ("맥심 TOP", 2000, 5,  150));
        vm.addBeverage(new Coffee      ("조지아",   1800, 8,  60));
        vm.addBeverage(new EnergyDrink ("레드불",   2500, 3,  1000));
        vm.addBeverage(new EnergyDrink ("몬스터",  2000, 5,  800));

        // 3. 메뉴 출력 (다형성: 각자 자기 방식으로 출력)
        vm.showMenu();

        // 4. 구매
        vm.purchase(1, 2000); // 맥심 TOP, 2000원 → 성공, 거스름돈 0원
        vm.purchase(1, 1000); // 맥심 TOP, 1000원 → 금액 부족
        vm.purchase(3, 3000); // 레드불,   3000원 → 성공, 거스름돈 500원
    }
}
