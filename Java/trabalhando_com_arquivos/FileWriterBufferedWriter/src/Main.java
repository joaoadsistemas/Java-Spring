import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        // Cria/recria o arquivo (se já existir): new FileWtriter(path)
        // Acrescenta ao arquivo existente: new FileWriter(path, true)

        String[] lines = new String[] {
                "Good Morning",
                "Good afternoon",
                "Good night"
        };

        String path = "c:\\TesteLeituraJava\\out.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {

            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }


    }
}