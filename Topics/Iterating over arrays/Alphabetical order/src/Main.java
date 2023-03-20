import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();
        String[] words = input.split(" ");

        boolean isSorted = true;
        for (int i = 0; i < words.length - 1; i++) {
            if (words[i].compareTo(words[i + 1]) > 0) {
                isSorted = false;
                break;
            }
        }
        System.out.println(isSorted);

    }
}