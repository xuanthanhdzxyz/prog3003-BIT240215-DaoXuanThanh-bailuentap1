package bai2.payment;

public class CreditCardPayment implements Payment {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("=== THANH TOÁN BẰNG CREDIT CARD ===");
        System.out.println("Số tiền: $" + amount);
        System.out.println("Đang xử lý giao dịch Credit Card...");
        System.out.println("Kiểm tra thông tin thẻ...");
        System.out.println("Xác thực giao dịch...");
        System.out.println("✅ Thanh toán $" + amount + " bằng Credit Card thành công!");
        System.out.println("Mã giao dịch: CC" + System.currentTimeMillis());
    }
}