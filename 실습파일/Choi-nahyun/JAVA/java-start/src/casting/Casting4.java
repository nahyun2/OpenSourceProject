package casting;

public class Casting4 {
    public static void main(String[] args) {
        int div1 = 3/2;
        System.out.println("div1 = " + div1);
        //int 타입끼리의 계산

        double div2 = 3/2;
        System.out.println("div2 = " + div2);
        //int 타입끼리의 계산한 결과를 자동형변환

        double div3 = 3.0/2;
        System.out.println("div3 = " + div3);
        //다른 타입끼리의 계산, int가 double로 형변환이 일어남

        double div4 = (double) 3/2;
        System.out.println("div4 = " + div4);
        //명시적 형변환을 사용해 int 타입이 double로 각각 형변환이 일어난 후 게산

        int a = 3;
        int b = 2;
        double result = (double) a/b; // (double) a / (double) b
        System.out.println("result = " + result);
    }
}
