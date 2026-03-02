package bai3.banking;

public class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;
    
    public BankAccount(String accountNumber, String accountHolder, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolder() {
        return accountHolder;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public void withdraw(double amount) {
        if (amount <= balance) {
            this.balance -= amount;
        }
    }
    
    public void deposit(double amount) {
        this.balance += amount;
    }
    
    @Override
    public String toString() {
        return "Tài khoản: " + accountNumber + " | Chủ TK: " + accountHolder + " | Số dư: $" + balance;
    }
}