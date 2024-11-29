package operator;

public class Operator2 {
    public static void main(String[] args) {
        String result1 = "hello " + "world";
        System.out.println(result1);

        String s1 = "string1";
        String s2 = "string2";
        String result2 = s1+s2; // 문자열 더하기
        System.out.println(result2);

        String result3 = "a+b=" + 10; // 문자열에 숫자 더하기
        System.out.println(result3);

        int num = 20;
        String str = "a+b=";
        String result4 = str+num; // 문자열에 정수변수 더하기
        System.out.println(result4);
    }
}
