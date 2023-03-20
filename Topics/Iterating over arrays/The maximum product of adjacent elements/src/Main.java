import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = scanner.nextInt();
        int[] intArray = new int[size];
        for (int i = 0; i < size; i++) {
            intArray[i] = scanner.nextInt();
        }

        int maxProduct = intArray[0] * intArray[1];
        if (intArray.length > 2) {
            for (int i = 2; i < size; i++) {
                int tmpProduct = intArray[i - 1] * intArray[i];
                if (tmpProduct > maxProduct) {
                    maxProduct = tmpProduct;
                }
            }
        }
        System.out.println(maxProduct);
    }
}
