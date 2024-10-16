package casting;

public class Casting2 {
    public static void main(String[] args) {
        int intValue = 0;
        double doubleValue = 1.5;

        //intValue = doubleValue; 컴파일 오류
        intValue = (int)doubleValue; //명시적 형변환
        System.out.println(intValue);

    }
}
//소수점 이하 데이터가 버려짐