public class Fix1 {
    public static void main(String args[]){
        int a = 10, b = 5;
        System.out.println("a = "+a+"\t b="+b);
        ++a;
        --b;
        System.out.println("전위 연산 a = "+a+"\t b="+b);
        a++;
        b--;
        System.out.println("전위 연산 a = "+a+"\t b="+b);
    }
    
}
