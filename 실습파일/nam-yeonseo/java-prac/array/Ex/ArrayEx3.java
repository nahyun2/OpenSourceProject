package array.Ex;

import java.util.Scanner;

public class ArrayEx3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] number = new int[5];

        System.out.println("5개의 정수를 입력하세요:");

        for (int i=0; i<5; i++) {
            int input = scanner.nextInt();
            number[i] = input;
        }

        System.out.println("출력");
        for(int i=number.length-1; i>=0; i--) {
            System.out.print(number[i]);
            if (i==0) break;
            System.out.print(", ");
        }
    }
}
