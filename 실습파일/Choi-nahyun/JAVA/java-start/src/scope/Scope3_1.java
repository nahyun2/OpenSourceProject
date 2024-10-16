package scope;

public class Scope3_1 {
    public static void main(String[] args) {
        int m = 10; //m 생존 시작
        int temp = 0;
        if (m > 0) {
            temp = m * 2;
            System.out.println("temp = " + temp);

        }
        System.out.println("m = " + m);
    }
}
//temp 변수는 if 코드 블록에서만 필요하므로 if문에서 선언을 했다면 불필요한 메모리 사용을 줄임
//main에서 여전히 temp에 접근이 가능해 스코프가 불필요하게 넓음