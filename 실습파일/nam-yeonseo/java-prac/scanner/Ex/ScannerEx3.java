package scanner.Ex;

import java.util.Scanner;

public class ScannerEx3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("음식 이름을 입력해주세요: ");
        String food = input.nextLine();
        System.out.print("음식의 가격을 입력해주세요: ");
        int price = input.nextInt();
        System.out.print("음식의 수량을 입력해주세요: ");
        int num = input.nextInt();

        System.out.println(food + " " + num + "개를 주문하셨습니다. 총 가격은 " + price*num + "원입니다.");
    }
}
