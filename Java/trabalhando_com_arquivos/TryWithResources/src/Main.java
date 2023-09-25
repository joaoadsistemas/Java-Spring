import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
      String path = "c:\\TesteLeituraJava\\in.txt";

      try (BufferedReader br = new BufferedReader(new FileReader(path))) {

          String line = br.readLine();
          while (line != null) {
              System.out.println(line);
              line = br.readLine();
          }


      } catch (IOException e) {
          System.out.println("ERROR: " + e.getMessage());
      }

    }
}