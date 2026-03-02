package bai2.payment;

public class CashPayment implements Payment {
    
    @Override
    public void processPayment(double amount) {
        System.out.println("=== THANH TOÁN BẰNG TIỀN MẶT ===");
        System.out.println("Số tiền: $" + amount);
        System.out.println("Đang xử lý thanh toán tiền mặt...");
        System.out.println("Kiểm tra tiền mặt...");
        System.out.println("Nhận tiền và trả lại tiền thừa (nếu có)...");
        System.out.println("✅ Thanh toán $" + amount + " bằng tiền mặt thành công!");
        System.out.println("Mã giao dịch: CS" + System.currentTimeMillis());
    }
}