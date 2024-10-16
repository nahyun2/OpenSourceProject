package array;

public class Array2 {
    public static void main(String[] args) {
        int[] students; //배열 변수 선언, 배열을 담는 배열변수를 선언한 것. 생성한 배열의 참조값을 보관함
        students = new int[5]; //5개의 int형 변수 생성 new = 생성, int[5] = 5개의 int형 변수, 자바는 배열 생성 시 자동으로 초기화함
        //students = 배열의 시작주소값

        students[0] = 90; //x001[0] = 90;과 같음
        students[1] = 80;
        students[2] = 70;
        students[3] = 60;
        students[4] = 50;

        for(int i = 0; i < students.length; i++) { //students.length = 5
            System.out.println("학생" + (i+1) + " 점수: " + students[i]);

        }

    }
}
