package analyzer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Error");
            System.exit(0);
        }

        String path = args[0];
        String pattern = args[1];
        String result = args[2];

        try (InputStream inputStream = new FileInputStream(path)) {
            var bytes = inputStream.readAllBytes();
            var text = new String(bytes);
            if (text.contains(pattern)) {
                System.out.println(result);
            } else {
                System.out.println("Unknown file type");
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
