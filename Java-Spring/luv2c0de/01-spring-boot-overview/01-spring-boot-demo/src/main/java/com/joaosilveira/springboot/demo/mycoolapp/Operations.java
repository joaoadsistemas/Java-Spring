package com.joaosilveira.springboot.demo.mycoolapp;

import com.joaosilveira.springboot.demo.mycoolapp.exceptions.UnsuportedMathOperationException;

public class Operations {


    public Double operation(String number) {
        try {
            if (!isNumeric(number)) {
                throw new UnsuportedMathOperationException("PLEASE SET A NUMERICA VALUE");
            }
        } catch (UnsuportedMathOperationException e) {
            System.out.println(e.getMessage());
        }


        return convertDouble(number);
    }

    public String formatExpression(Double express) {
        return String.format("%.2f", express);
    }


    private Double convertDouble(String n) {

        if (n == null) return null;
        String numberFormat = n.replaceAll(",", ".");
        return Double.parseDouble(numberFormat);

    }




    private boolean isNumeric(String n) {
        if (n == null) return false;
        String numberFormat = n.replaceAll(",", ".");
        return numberFormat.matches("-?\\d+(\\.\\d+)?");

    }


}
