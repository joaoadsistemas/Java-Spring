import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Stream<Integer> st1 = list.stream().map(x -> x * 10);
        System.out.println(Arrays.toString(st1.toArray()));


        Stream<String> st2 = Stream.of("Maria", "Joao", "Carlos");
        System.out.println(Arrays.toString(st2.toArray()));

        // Aqui estou gerando um valor infinite para essa stream
        Stream<Integer> st3 = Stream.iterate(0, x -> x + 2);
        // Aqui eu limito para 10 a quantidade de numeros a ser mostrado
        System.out.println(Arrays.toString(st3.limit(10).toArray()));

        // Sequencia de Fibonnaci
        Stream<Long> st4 = Stream.iterate(new Long[]{0L, 1L}, p -> new Long[] {p[1], p[0] + p[1]}).map(p -> p[0]);
        System.out.println(Arrays.toString(st4.limit(10).toArray()));

        // Pipeline usando varias funções na stream
        List<Integer> newList = list.stream().filter(x -> x % 2 == 0).map(x -> x * 10).toList();
        System.out.println(Arrays.toString(newList.toArray()));

    }
}