package array.ex;

import java.util.Scanner;

public class ArrayEx6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력받을 숫자의 개수를 입력하세요:");
        int count = scanner.nextInt();

        int[] numbers = new int[count];
        int max, min;

        System.out.println(count + "개의 정수를 입력하세요: ");
        for (int i = 0; i < count; i++){
            numbers[i] = scanner.nextInt();
        }
        max = min = numbers[0];
        for (int i = 1; i < count; i++){
            if(max < numbers[i]){
                max = numbers[i];
            }
            if(min > numbers[i]){
                min = numbers[i];
            }
        }
        System.out.println("가장 작은 정수: " + min);
        System.out.println("가장 큰 정수: " + max);

    }
}
