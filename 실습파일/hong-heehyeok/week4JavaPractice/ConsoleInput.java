import java.util.*;

public class ConsoleInput {
    public static void main (String[] args){
        System.out.print("임의의 숫자를 입력하세요:");
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        System.out.println("n" + n);
        scanner.close();
    }
}
