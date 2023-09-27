import com.sun.org.apache.xpath.internal.operations.Or;
import entities.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LocalDateTime moment;
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - H/m/s");
        Client client = new Client();
        Order order;


        System.out.println("Enter cliente data: ");

        System.out.print("Name: ");
        String clientName = sc.nextLine();

        System.out.print("Email: ");
        String clientEmail = sc.nextLine();

        System.out.print("Birth date (dd/mm/yyyy): ");
        String stringBirthDate = sc.nextLine();
        int day = Integer.parseInt(stringBirthDate.substring(0,2));
        int month = Integer.parseInt(stringBirthDate.substring(3,5));
        int year = Integer.parseInt(stringBirthDate.substring(6));
        LocalDate localDate = LocalDate.of(year, month, day);


        System.out.println("Enter order data: ");
        System.out.print("Status: ");
        OrderStatus orderStatus = OrderStatus.valueOf(sc.nextLine());

        System.out.print("How many items to this order? ");
        int itemsOrder = sc.nextInt();
        sc.nextLine();

        client.setName(clientName);
        client.setEmail(clientEmail);
        client.setBirthDate(localDate);

        moment = LocalDateTime.now();
        order = new Order(moment, orderStatus, client);


        for (int i = 0; i < itemsOrder; i++) {
            System.out.println("ENTER #" + (i+1) + " ITEM DATA:");
            System.out.print("Product name: ");
            String productName = sc.next();
            System.out.print("Product price: ");
            double productPrice = sc.nextDouble();
            System.out.print("Quantitty: ");
            int quantity = sc.nextInt();

            OrderItem orderItem = new OrderItem(quantity, productPrice, new Product(productName, productPrice));
            order.addOrdemItem(orderItem);
        }

        System.out.println("ORDER SUMMARY: ");
        System.out.println("Order moment: " + order.getMoment().format(dateFormatter));
        System.out.println("Order statud: " + order.getStatus());
        System.out.println("Client: " + client.getName() + " (" + client.getBirthDate() + ") " + " - " + " " + client.getEmail());
        System.out.println("Order items: ");
        for (OrderItem p: order.getOrderItems()) {
            System.out.println(p.getProduct().getName() + ", $" + p.getProduct().getPrice() + ", Quantity: "
            + p.getQuantity() + ", Subtotal: " + p.subTotal());
        }
        System.out.println("Total price: " + order.total());


    }
}