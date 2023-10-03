import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        // COMPARATOR (Coloca em orderm)
        list.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));

        for (Product p : list) {
            System.out.println(p.getName());
        }
        System.out.println();
        System.out.println();

        // FUNCTION (Aqui por exemplo estou colocando todos da lista como caixa alta)
        List<String> names = list.stream().map(p -> p.getName().toUpperCase()).toList();
        names.forEach(System.out::println);

        System.out.println();
        System.out.println();

        // PREDICATE (Aqui estou removendo quem tiver o preço menor que 5000)
        list.removeIf(p -> p.getPrice() <= 5000);
        for (Product p: list) {
            System.out.println(p.getName() + ", " + p.getPrice());
        }

        System.out.println();
        System.out.println();

        // Consumer (forEach)
        list.forEach(p -> p.setPrice(p.getPrice() * 1.1));
        list.forEach(System.out::println);

    }
}