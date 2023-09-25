import java.io.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        String pathIn = "c:\\TesteLeituraJava\\in.csv";

        boolean newFolder = new File("c:\\TesteLeituraJava\\out").mkdir();

        String strPath = "c:\\TesteLeituraJava\\out\\summary.csv";

        Product product;

        int n = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(pathIn))) {

            while (n != 3) {

                String string = br.readLine();
                String[] vet = string.split(",");

                String name = vet[0];
                double price = Double.parseDouble(vet[1]);
                int amount = Integer.parseInt(vet[2]);

                product = new Product(name, price, amount);


                try(BufferedWriter bw = new BufferedWriter(new FileWriter(strPath, true))) {
                    bw.write(product.name + "," + product.total());
                    bw.newLine();
                } catch (IOException e) {
                    System.out.println("ERROR WRITE: " + e.getMessage());
                }
                n++;
            }
        } catch (IOException e) {
            System.out.println("ERROR READ: " + e.getMessage());
        }
    }
}