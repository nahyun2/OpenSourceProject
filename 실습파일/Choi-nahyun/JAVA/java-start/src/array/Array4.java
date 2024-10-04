package array;

public class Array4 {
    public static void main(String[] args) {
        int[] students = {90, 80, 70, 60, 50};  // 학생의 점수를 추가해도 초기화 부분만 수정해주면 된다.


        for(int i = 0; i < students.length; i++) { //students.length = 5
            System.out.println("학생" + (i+1) + " 점수: " + students[i]);

        }

    }
}

