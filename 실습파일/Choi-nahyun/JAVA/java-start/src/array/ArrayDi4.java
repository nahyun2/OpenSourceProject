package array;

public class ArrayDi4 {
    public static void main(String[] args) {
        int[][] arr = new int[4][5];

        int i = 1;
        for (int row = 0; row < arr.length; row++){ //행의 길이
            for (int column = 0; column < arr[row].length; column++){ //열의 길이만큼
                arr[row][column] = i++;
            }
        }


        for (int row = 0; row < arr.length; row++){ //행의 길이
            for (int column = 0; column < arr[row].length; column++){ //열의 길이만큼
                System.out.print(arr[row][column] + " ");
            }
            System.out.println();
        }




    }
}
