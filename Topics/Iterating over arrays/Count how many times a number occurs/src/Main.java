import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int arraySize = scanner.nextInt();
        int[] intArray = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            intArray[i] = scanner.nextInt();
        }
        int number = scanner.nextInt();

        int numCnt = 0;
        for (int i = 0; i < arraySize; i++) {
            if (intArray[i] == number) {
                numCnt++;
            }
        }
        System.out.println(numCnt);
    }
}
