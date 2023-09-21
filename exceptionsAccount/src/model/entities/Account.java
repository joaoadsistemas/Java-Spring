package model.entities;

import model.exceptions.DomainException;

public class Account {
    private int number;
    private String holder;
    private double balance;
    private double withdrwLimit;

    public Account(int number, String holder, double balance, double withdrwLimit) {
        this.number = number;
        this.holder = holder;
        this.balance = balance;
        this.withdrwLimit = withdrwLimit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrwLimit() {
        return withdrwLimit;
    }

    public void setWithdrwLimit(double withdrwLimit) {
        this.withdrwLimit = withdrwLimit;
    }

    public void deposit(double amount) {
        this.balance = amount;
    }

    public void withdraw(double amount) throws DomainException {

        if (amount > withdrwLimit) {
            throw new DomainException("The amount exceeds withdraw limit");
        }
        if (amount > balance) {
            throw new DomainException("Not enough balance");
        }

        this.balance = this.balance - amount;
    }


}
