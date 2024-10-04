package casting;

public class Casting1 {
    public static void main(String[] args) {
        int intValue = 10;
        long longValue;
        double doubleValue;

        longValue = intValue;
        System.out.println("longValue = " + longValue);

        doubleValue = intValue;
        System.out.println("doubleValue1 = " + doubleValue);

        doubleValue = 20L; //long -> double
        System.out.println("doubleValue2 = " + doubleValue);
    }
}
//작은 범위에서 큰 범위로의 대입 허용