package scanner;

import java.util.Scanner;

public class Scanner2 {
    public static void main(String[] args) {
        Scanner sanner = new Scanner(System.in);

        System.out.print("첫번째 숫자를 입력하세요: ");
        int num1 = sanner.nextInt();

        System.out.print("두번째 숫자를 입력하세요: ");
        int num2 = sanner.nextInt();

        int sum = num1 + num2;
        System.out.println("두 숫자의 합: " + sum);
    }
}
