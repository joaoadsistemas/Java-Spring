import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

            File file = new File("c:\\TesteLeituraJava\\in.txt");
            Scanner sc = null;
            try {
                sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    System.out.println(sc.nextLine());
                }
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            } finally {
                if(sc != null) {
                    sc.close();
                }

            }


        }
    }
