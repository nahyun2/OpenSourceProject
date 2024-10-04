package array;

public class Array3 {
    public static void main(String[] args) {
        int[] students;
        students = new int[]{90, 80, 70, 60, 50}; //배열 생성과 초기화
        //int[] students = new int[]{90, 80, 70, 60, 50}; 한 줄로 선언, 생성, 초기화 가능
        //int[] students = {90, 80, 70, 60, 50}; //한 줄 생성시 생략 가능, 선언과 생성 따로 할 경우에는 생략 불가


        for(int i = 0; i < students.length; i++) { //students.length = 5
            System.out.println("학생" + (i+1) + " 점수: " + students[i]);

        }

    }
}

