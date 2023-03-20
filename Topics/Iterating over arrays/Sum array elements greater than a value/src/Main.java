import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            intArray[i] = scanner.nextInt();
        }
        int bound = scanner.nextInt();
        int sum = 0;
        for (int i = 0; i < size; i++) {
            if (intArray[i] > bound) {
                sum += intArray[i];
            }
        }
        System.out.println(sum);
    }
}
