public class Condition {
    public static void main (String args[]){
        int a = 10 , b = 11;
        int max, min;
        max = a > b ? a : b;
        min = a < b ? a : b;
        System.out.println("두 수 중 큰 값"+ max);
        System.out.println("두 수 중 작은 값"+ min);
    }
    
}
