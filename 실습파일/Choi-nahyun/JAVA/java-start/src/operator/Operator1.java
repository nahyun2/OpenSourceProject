package operator;

public class Operator1 {
    public static void main(String[] args) {
        //변수 초기화
        int a = 5;
        int b = 2;

        //덧셈
        int sum = a + b;
        System.out.println("a + b = " + sum);

        //뺄셈
        int diff = a - b;
        System.out.println("a - b = " + diff);

        //곱셈
        int multi = a * b;
        System.out.println("a * b = " + multi);

        //나눗셈
        int div = a / b;
        System.out.println("a / b = " + div);

        //나머지
        int mod = a % b;
        System.out.println("a % b = " + mod);

        /* 계산 과정
        1. 변수 값 읽기 a -> 5 b -> 2
        2. 변수 값 계산 5+2 -> 7
        3. 계산 결과를 변수에 대입 sum = 7
        최종 결과 sum = 7
         */
    }
}
