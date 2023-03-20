import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        int triples = 0;
        for (int i = 1; i < size - 1; i++) {
            if (array[i] - array[i - 1] == 1 && array[i + 1] - array[i] == 1) {
                triples++;
            }
        }

        System.out.println(triples);
    }
}
