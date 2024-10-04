package cond;

public class If5 {
    public static void main(String[] args) {
        int price = 15000;
        int age = 20;

        if (price <= 10000 && age <= 10){
            System.out.println("2000원 할인");
        }
        else if (price <= 10000){
            System.out.println("1000원 할인");
        }
        else if (age <= 10){
            System.out.println("1000원 할인");
        }
    }
}
