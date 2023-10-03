import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        List<Product> list = new ArrayList<>();

        list.add(new Product("TV", 1200.00));
        list.add(new Product("Iphone 14", 6000.00));
        list.add(new Product("Lenovo Gaming 3i", 4200.00));
        list.add(new Product("Razer Blackwidow", 2000.00));


        // FUNÇÃO LAMBDA (ARROW FUNCTION/FUNÇÃO ANONIMA)
        list.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));

        for (Product p : list) {
            System.out.println(p.getName());
        }


    }
}