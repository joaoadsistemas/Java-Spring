package org.example.tests.entities;

import org.example.entities.Financing;
import org.example.tests.factory.FinancingFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.Test;

public class FinancingTests {


    @Test
    public void financingShouldCreateNewFinanceWhenValidInitialData() {

        //Arrange
        //Act
        Financing financing = FinancingFactory.FinancingFactory(100000.0, 2000.0, 80);

        //Assert
        Assertions.assertEquals(Financing.class, financing.getClass());
    }


    @Test
    public void financingShouldThrowExceptionWhenNotValidInitialData() {

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            //Act
            Financing financing = FinancingFactory.FinancingFactory(100000.0, 2000.0, 20);
        });
    }


    @Test
    public void totalAmountSholdUpdateWhenValidData() {
        //Arrange
        double expectedValue = 150000.0;
        Financing financing = FinancingFactory.FinancingFactory(100000.0, 5000.0, 80);

        //Act
        financing.setTotalAmount(expectedValue);

        //Assert
        Assertions.assertEquals(expectedValue, financing.getTotalAmount());
    }


    @Test
    public void totalAmountShouldThrowExceptionWhenNotValidData() {

        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            //Arrange
            double expectedValue = 150000.0;
            Financing financing = FinancingFactory.FinancingFactory(100000.0, 5000.0, 40);

            //Act
            financing.setTotalAmount(expectedValue);
        });
    }


    @Test
    public void incomeShouldUpdateWhenValidData() {
        //Arrange
        double expectedValue = 7000.0;
        Financing financing = FinancingFactory.FinancingFactory(100000.0, 5000.0, 80);

        //Act
        financing.setIncome(expectedValue);

        //Assert
        Assertions.assertEquals(expectedValue, financing.getIncome());
    }


    @Test
    public void incomeShouldThrowExceptionWhenNotValidData() {
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            //Arrange
            double expectedValue = 1000.0;
            Financing financing = FinancingFactory.FinancingFactory(100000.0, 5000.0, 80);

            //Act
            financing.setIncome(expectedValue);
        });
    }

    @Test
    public void monthsShouldUpdateWhenValidData() {
        //Arrange
        int expectedValue = 80;
        Financing financing = FinancingFactory.FinancingFactory(100000.0, 5000.0, 40);

        //Act
        financing.setMonths(expectedValue);

        //Assert
        Assertions.assertEquals(expectedValue, financing.getMonths());
    }

    @Test
    public void monthsShouldThrowExceptionWhenNotValidData() {
        //Assertion
        Assertions.assertThrows(IllegalArgumentException.class, () -> {

            //Arrange
            Financing financing = FinancingFactory.FinancingFactory(100000.0, 5000.0, 80);

            //Act
            financing.setMonths(5);
        });
    }


    @Test
    public void entryShouldCalculateCorrectly() {
        //Arrange
        double expectedValue = 20000;
        Financing financing = FinancingFactory.FinancingFactory(100000.0, 2000.0, 80);

        //Act
        double entry = financing.entry();

        Assertions.assertEquals(expectedValue, entry);
    }

    @Test
    public void quotaShouldCalculateCorrectly() {

        //Arrange
        double expectedValue = 1000.0;
        Financing financing = FinancingFactory.FinancingFactory(100000.0, 2000.0, 80);

        //Act
        double quota = financing.quota();


        //Assert
        Assertions.assertEquals(expectedValue, quota);
    }

}
