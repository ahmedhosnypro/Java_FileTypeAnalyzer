import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] intArray = new int[size];
        int sum = 0;
        for (int i = 0; i < size; i++) {
            intArray[i] = scanner.nextInt();
            sum += intArray[i];
        }
        System.out.println(sum);
    }
}
