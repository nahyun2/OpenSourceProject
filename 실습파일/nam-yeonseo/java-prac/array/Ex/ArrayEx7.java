package array.Ex;

import java.util.Scanner;

public class ArrayEx7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int [][] stu = new int[4][3];
        String [] sub = {"국어", "영어", "수학"};

        for (int i=0; i<stu.length; i++) {
            System.out.println((i+1) + "번 학생의 성적을 입력하세요:");
            for (int j=0; j<3; j++) {
                System.out.print(sub[j] + " 점수: ");
                int input = scanner.nextInt();
                stu[i][j] = input;
            }
        }

        for (int i=0; i<4; i++) {
            int total = 0;
            for (int j=0; j<3; j++) {
                total += stu[i][j];
            }
            System.out.println((i+1) + "번 학생의 총점: " + total + ", 평균: " + (double) total/3);
        }
    }
}
