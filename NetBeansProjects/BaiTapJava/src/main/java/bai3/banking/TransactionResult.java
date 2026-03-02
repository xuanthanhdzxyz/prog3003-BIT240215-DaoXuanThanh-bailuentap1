package bai3.banking;

public class TransactionResult {
    private boolean success;
    private String message;
    private double amount;
    private String fromAccount;
    private String toAccount;
    private double newBalance;
    
    public TransactionResult(boolean success, String message, double amount, 
                            String fromAccount, String toAccount, double newBalance) {
        this.success = success;
        this.message = message;
        this.amount = amount;
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.newBalance = newBalance;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public String getMessage() {
        return message;
    }
    
    @Override
    public String toString() {
        if (success) {
            return "✅ GIAO DỊCH THÀNH CÔNG!\n" +
                   "   Số tiền: $" + amount + "\n" +
                   "   Từ tài khoản: " + fromAccount + "\n" +
                   "   Đến tài khoản: " + toAccount + "\n" +
                   "   Số dư mới: $" + newBalance + "\n" +
                   "   " + message;
        } else {
            return "❌ GIAO DỊCH THẤT BẠI!\n" +
                   "   Lý do: " + message + "\n" +
                   "   Số tiền: $" + amount;
        }
    }
}