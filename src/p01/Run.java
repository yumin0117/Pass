package p01;

public abstract class Run {
    private String drink;
    private Integer drinkMoney;
    private Integer insertMoney;
    private Integer money;

    public Run(String drink, Integer drinkMoney, Integer insertMoney, Integer money) {
        this.drink = drink;
        this.drinkMoney = drinkMoney;
        this.insertMoney = insertMoney;
        this.money = money;
    }

    public String getDirnk() {
        return drink;
    }

    public void setDirnk(String dirnk) {
        this.drink = dirnk;
    }

    public void drink(){
        System.out.print("고르신 음료는 " + drink + "의 가격은 " + drinkMoney + "입니다. 투입하신 금액은 " + insertMoney + "입니다. 거스름돈은 " + money + "입니다.");
    };
}
