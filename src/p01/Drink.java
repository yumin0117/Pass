package p01;

import java.util.ArrayList;
import java.util.Scanner;

public class Drink {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        ArrayList<Run> drinkList = new ArrayList<>();

        System.out.println("============================================");
        System.out.println("                 음료 자판기                 ");
        System.out.println("============================================");

        System.out.println("\n돈을 투입해주세요: ");
        int insertMoney = s.nextInt();

        String drink;
        Integer drinkMoney = 0;
        Integer money = 0;
        System.out.println("\n1. 데미소당\n2. 뚠딴지뽀내나맛유우\n3. 파뤌에이두\n4. 퐌타\n5. 갈지않은배");
        System.out.println("\n음료를 선택해주세요(숫자입력): ");
        int selectNum = s.nextInt();

        if(selectNum == 1){
            drink = "데미소당";
            drinkMoney = 1500;
            money = insertMoney - drinkMoney;
            drinkList.add(new VendingMachine(drink, drinkMoney, insertMoney, money));
        } else if (selectNum == 2) {
            drink = "뚠딴지뽀내나맛우유";
            drinkMoney = 1300;
            money = insertMoney - drinkMoney;
            drinkList.add(new VendingMachine(drink, drinkMoney, insertMoney, money));
        } else if (selectNum == 3){
            drink = "파월에이두";
            drinkMoney = 2000;
            money = insertMoney - drinkMoney;
            drinkList.add(new VendingMachine(drink, drinkMoney, insertMoney, money));
        } else if (selectNum == 4) {
            drink = "퐌타";
            drinkMoney = 1800;
            money = insertMoney - drinkMoney;
            drinkList.add(new VendingMachine(drink, drinkMoney, insertMoney, money));
        } else if (selectNum == 5){
            drink = "갈지않은배";
            drinkMoney = 900;
            money = insertMoney - drinkMoney;
            drinkList.add(new VendingMachine(drink, drinkMoney, insertMoney, money));
        } else {
            System.out.println("\n1부터 5까지의 숫자를 입력해주세요: ");
            System.out.println("\n음료를 선택해주세요(숫자입력): ");
            selectNum = s.nextInt();
        }

        for (Run run : drinkList) {
            run.drink();
        }
        s.close();
    }
}
