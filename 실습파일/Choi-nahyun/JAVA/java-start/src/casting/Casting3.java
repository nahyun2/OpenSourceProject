package casting;

public class Casting3 {
    public static void main(String[] args) {
        long maxIntValue = 2147483647;
        long maxIntOver = 2147483648L; //int 최고값 +1
        int intValue = 0;

        intValue = (int) maxIntValue;
        System.out.println("maxIntValue casting = " + intValue);
        //int가 표현할 수 있는 범위이므로 문제 x
        intValue = (int) maxIntOver;
        System.out.println("maxIntOver casting = " + intValue);
        //오버플로우가 발생해 데이터 값과 다른 값이 표현

    }
}
