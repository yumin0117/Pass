package p01;

import java.util.Scanner;

public class Drink {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.println("============================================");
        System.out.println("                 음료 자판기                 ");
        System.out.println("============================================");

        System.out.println("\n돈을 투입해주세요: ");
        int insertMoney = s.nextInt();


        String drink;
        Integer drinkMoney = 0;
        Integer money = 0;
        System.out.println("\n1. 데미소당\n 2. 뚠딴지뽀내나맛유우\n 3. 파뤌에이두\n 4. 퐌타\n 5. 갈지않은배");
        System.out.println("음료를 선택해주세요(숫자입력): ");
        int selectNum = s.nextInt();

        if(selectNum == 1){
            drink = "데미소당";
            drinkMoney = 1500;
        } else if (selectNum == 2) {
            drink = "뚠딴지뽀내나맛우유";
            drinkMoney = 1300;
        } else if (selectNum == 3){
            drink = "파월에이두";
            drinkMoney = 2000;
        } else if (selectNum == 4) {
            drink = "퐌타";
            drinkMoney = 1800;
        } else if (selectNum == 5){
            drink = "갈지않은배";
            drinkMoney = 900;
        } else {
            System.out.println("1부터 5까지의 숫자를 입력해주세요: ");
            System.out.println("음료를 선택해주세요(숫자입력): ");
        }

        money = insertMoney - drinkMoney;

        s.close();
    }
}
