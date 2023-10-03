import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);



       System.out.print("Enter full file path: ");
        String path = sc.nextLine();



        System.out.print("Enter salary: ");
        double salary = sc.nextDouble();

        System.out.println("Email of people wohe salary is more than " + String.format("%.2f", salary));

        List<Employee> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){

            String[] split;
            String linha;
            while ((linha = br.readLine()) != null) {
                split = linha.split(",");

                list.add(new Employee(split[0], split[1], Double.parseDouble(split[2])));
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<Employee> newList =
                list.stream().filter(x -> x.getSalary() >= salary).sorted((e1, e2) -> e1.getName().compareTo(e2.getName())).toList();
        for (Employee e: newList
             ) {
            System.out.println(e.getEmail());
        }


        double sumSalaryM =
                list.stream().filter(e -> e.getName().startsWith("M")).mapToDouble(Employee::getSalary).sum();

        System.out.println("Sum of salary of people whose name starts with 'M': " + String.format("%.2f", sumSalaryM));


    }
}