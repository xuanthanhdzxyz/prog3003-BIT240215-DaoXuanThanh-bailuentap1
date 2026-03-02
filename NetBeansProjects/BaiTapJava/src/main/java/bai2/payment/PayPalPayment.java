package bai2.payment;

public class PayPalPayment implements Payment {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("=== THANH TOÁN BẰNG PAYPAL ===");
        System.out.println("Số tiền: $" + amount);
        System.out.println("Đang xử lý giao dịch PayPal...");
        System.out.println("Chuyển hướng đến PayPal...");
        System.out.println("Xác thực tài khoản PayPal...");
        System.out.println("✅ Thanh toán $" + amount + " bằng PayPal thành công!");
        System.out.println("Mã giao dịch: PP" + System.currentTimeMillis());
    }
}