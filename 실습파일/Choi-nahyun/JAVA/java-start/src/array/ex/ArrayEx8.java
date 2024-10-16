package array.ex;

import java.util.Scanner;

public class ArrayEx8 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //행은 학생의 번호, 열은 국어, 영어, 수학 점수

        System.out.print("학생 수를 입력하세요: ");
        int scount = input.nextInt();

        int[][] scores = new int[scount][3];
        String[] subjects = {"국어", "수학", "영어"};
        int[] total = new int[4];
        double[] average = new double[4];

        for (int i = 0; i<scount; i++){
            System.out.println((i+1) + "번 학생의 성적을 입력하세요:");
            for (int j = 0; j<3; j++){
                System.out.print(subjects[j] + "점수:");
                scores[i][j] = input.nextInt();
                total[i] += scores[i][j];
            }
            average[i] = (double) total[i] / 3;

        }
        for (int i = 0; i < scount; i++){
            System.out.println((i+1) + "번 학생의 총점: " + total[i] + ", 평균: " + average[i]);
        }

    }
}
