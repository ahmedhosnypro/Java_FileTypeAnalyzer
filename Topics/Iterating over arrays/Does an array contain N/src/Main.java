import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int legnth = scanner.nextInt();
        int[] array = new int[legnth];
        for (int i = 0; i < legnth; i++) {
            array[i] = scanner.nextInt();
        }
        int num = scanner.nextInt();
        boolean found = false;
        for (var n : array) {
            if (n == num)
                found = true;
        }
        System.out.println(found);
    }
}
