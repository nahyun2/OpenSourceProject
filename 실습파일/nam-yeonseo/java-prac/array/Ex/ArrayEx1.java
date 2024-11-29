package array.Ex;

public class ArrayEx1 {
    public static void main(String[] args) {
        int [] stu= {90, 80, 70, 60, 50};
        int total = 0;

        for (int i=0; i<5; i++) {
            total += stu[i];
        }

        double average = (double) total/5;

        System.out.println("점수 총합: " + total);
        System.out.println("점수 평균: " + average);
    }
}
