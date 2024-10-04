package array.Ex;

import java.util.Scanner;

public class ArrayEx4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int [] number = new int[5];
        int total = 0;
        double average = 0;

        System.out.println("5개의 정수를 입력하세요:");

        for (int i=0; i<5; i++) {
            int input = scanner.nextInt();
            number[i] = input;
            total += number[i];
        }
        average = (double) total/number.length;

        System.out.println("입력한 정수의 합계: " + total);
        System.out.println("입력한 정수의 평균: " + average);
    }
}
