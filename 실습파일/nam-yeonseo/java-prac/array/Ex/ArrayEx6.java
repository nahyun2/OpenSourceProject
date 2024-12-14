package array.Ex;

import java.util.Scanner;

public class ArrayEx6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력받을 숫자의 개수를 입력하세요: ");
        int size = scanner.nextInt();
        int [] number = new int[size]; // 배열 크기 정하기

        System.out.println(size + "개의 정수를 입력하세요:");
        for (int i=0; i<size; i++) {
            int input = scanner.nextInt(); // 숫자 입력 받기
            number[i] = input; // 입력받은 숫자를 각 배열에 저장하기
        }

        int max = number[0]; // 최대값 찾기
        for (int i=1; i<number.length; i++) {
            if (number[i] > max) max = number[i];
        }

        int min = number[0]; // 최소값 찾기
        for (int i=1; i<number.length; i++) {
            if (number[i] < min) min = number[i];
        }

        System.out.println("가장 작은 정수: " + min);
        System.out.println("가장 큰 정수: " + max);
    }
}
