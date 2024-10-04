package scanner;

import java.util.Scanner;

public class ScannerWhile3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int sum = 0;

        while (true) {
            System.out.print("정수를 입력하세요 (0을 입력하면 종료): ");
            int number = input.nextInt();

            if (number == 0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            sum += number;
            System.out.println("sum = " + sum);
        }
    }
}
