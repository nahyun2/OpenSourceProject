package scanner.Ex;

import java.util.Scanner;

public class ScannerEx2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("하나의 정수를 입력하세요: ");
        int input = scanner.nextInt();

        if(input/2 == 0) {
            System.out.println("입력한 숫자 " + input + "는 짝수입니다.");
        }
        else {
            System.out.println("입력한 숫자 " + input + "는 홀수입니다.");
        }
    }
}
