package array.Ex;

import java.util.Scanner;

public class ProductAdminEx {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] productNames = new String[10]; // 상품 이름 저장
        int[] productPrices = new int[10]; // 상품 가격 저장
        int productCount = 0; // 현재 등록된 상품의 개수 저장

        while(true) {
            System.out.print("1. 상품 등록 | 2. 상품 목록 | 3. 종료\n메뉴를 선택하세요: ");
            int menuSelect = scanner.nextInt();
            scanner.nextLine();

            if(menuSelect == 1) {
                if (productCount >= 10) System.out.println("더 이상 상품을 등록할 수 없습니다.");
                else {
                    System.out.print("상품 이름을 입력하세요: ");
                    productNames[productCount] = scanner.nextLine();

                    System.out.print("상품 가격을 입력하세요: ");
                    productPrices[productCount] = scanner.nextInt();

                    productCount++;
                }
            }

            else if (menuSelect == 2) {
                for (int i=0; i<productCount; i++) {
                    System.out.println(productNames[i] + ": " + productPrices[i] + "원");
                }
            }

            else if (menuSelect == 3) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            else {
                System.out.println("잘못된 접근입니다.");
            }
        }
    }
}
