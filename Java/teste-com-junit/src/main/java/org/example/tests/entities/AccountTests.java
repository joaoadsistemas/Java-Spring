package org.example.tests.entities;

import org.example.entities.Account;
import org.example.tests.factory.AccountFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

public class AccountTests {

    @Test
    public void depositShouldIncreaseBalanceWhenPositiveAmount() {

        // Arrange
        double amount = 200.0;
        double expectedValue = 196.0;
        Account acc = AccountFactory.createEmptyAccount();

        //Act
        acc.deposit(amount);

        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
    }


    @Test
    public void depositShouldDoNothingWhenNegativeAmount() {

        //Arrange
        double expectedValue = 100;
        Account acc = AccountFactory.createAccount(expectedValue);
        double amount = -200.00;

        // Act
        acc.deposit(amount);

        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
    }

    @Test
    public void fullWithdrawShouldClearBalanceAndReturnFullBalance() {

        //Arrange
        double expectedValue = 0.0;
        double initialBalance = 800.0;
        Account acc = AccountFactory.createAccount(initialBalance);

        //Act
        double result = acc.fullWithdraw();

        //Assert
        Assertions.assertEquals(expectedValue, acc.getBalance());
        Assertions.assertEquals(result, initialBalance);
    }

    @Test
    public void withdrawShouldDecreaseBalanceWhenSufficientBalance() {

        //Arrange
        Account acc = AccountFactory.createAccount(800.0);

        //Act
        acc.withdraw(500.0);

        //Assert
        Assertions.assertEquals(300.0, acc.getBalance());
    }


    @Test
    public void withdrawShouldThrowExceptionWhenInsufficientBalance() {


        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            Account acc = AccountFactory.createAccount(800.0);

            //Act
            acc.withdraw(801.0);
        });
    }

}
