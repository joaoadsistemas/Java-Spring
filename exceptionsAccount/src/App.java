import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Account;
import model.exceptions.DomainException;

public class App {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);



        try {
        System.out.println("Enter account data");
        System.out.println("Number: ");
        int number = sc.nextInt();
        System.out.println("Holder: ");
        String holder = sc.next();
        System.out.println("Initial balance: ");
        double balance = sc.nextDouble();
        System.out.println("Withdraw limit:");
        double withdrwLimit = sc.nextDouble();

        System.out.println("Enter amount for withdraw");
        double withdraw = sc.nextDouble();
        
        Account account = new Account(number, holder, balance, withdrwLimit);
        
        account.deposit(balance);
        account.withdraw(withdraw);

        System.out.println("New Balance: " + account.getBalance()); 
        } catch (DomainException e) {
            System.out.println("Withdraw error: " + e.getMessage());
        }

        sc.close();
    }
}
