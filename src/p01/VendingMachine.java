package p01;

public class VendingMachine extends Run{
    public VendingMachine(String drink, Integer drinkMoney, Integer insertMoney, Integer money){
        super(drink, drinkMoney, insertMoney, money);
    }

    @Override
    public void drink(){
        System.out.println(" ");
    }
}
