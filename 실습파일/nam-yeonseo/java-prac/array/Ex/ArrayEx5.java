package array.Ex;

import java.util.Scanner;

public class ArrayEx5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력받을 숫자의 개수를 입력하세요: ");
        int size = scanner.nextInt();
        int [] number = new int[size];

        int total = 0;
        double average = 0;

        System.out.println(size + "개의 정수를 입력하세요:");
        for (int i=0; i<size; i++) {
            int input = scanner.nextInt();
            number[i] = input;
            total += number[i];
        }
        average = (double) total/number.length;

        System.out.println("입력한 정수의 합계: " + total);
        System.out.println("입력한 정수의 평균: " + average);
    }
}
