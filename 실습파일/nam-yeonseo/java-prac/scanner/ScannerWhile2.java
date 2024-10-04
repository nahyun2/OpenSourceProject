package scanner;

import java.util.Scanner;

public class ScannerWhile2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int sum = 0;

            System.out.print("첫번째 숫자: ");
            int num1 = scanner.nextInt();

            System.out.print("두번째 숫자: ");
            int num2 = scanner.nextInt();

            if (num1 == 0 && num2 == 0) break;
            else {
                sum = num1 + num2;
                System.out.println("num1 + num2 = " + sum);
            }
        }
        System.out.println("프로그램을 종료합니다.");
    }
}
