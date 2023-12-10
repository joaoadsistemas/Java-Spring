package org.example.tests.factory;

import org.example.entities.Financing;

public class FinancingFactory {

    public static Financing FinancingFactory(Double totalAmount, Double income, Integer months) {
        return new Financing(totalAmount, income, months);
    }

}
