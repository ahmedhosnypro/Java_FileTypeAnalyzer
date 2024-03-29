import java.util.Scanner;
import java.util.function.Function;

class CurryConcat {

    public static String calc(String first, String second, String third) {

        Function<String, Function<String, Function<String, String>>> stringFun =
                str1 -> str2 -> str3 -> str1.toLowerCase() + str3.toUpperCase() + str2.toLowerCase();

        return stringFun.apply(first).apply(second).apply(third);
    }

    // Don't change the code below
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(calc(scanner.next(), scanner.next(), scanner.next()));
    }
}