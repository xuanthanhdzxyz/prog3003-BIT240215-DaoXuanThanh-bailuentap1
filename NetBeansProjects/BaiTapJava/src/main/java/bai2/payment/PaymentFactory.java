package bai2.payment;

public class PaymentFactory {
    
    public static Payment createPayment(String paymentType) {
        if (paymentType == null || paymentType.trim().isEmpty()) {
            throw new IllegalArgumentException("Loại thanh toán không được để trống!");
        }
        
        String type = paymentType.toLowerCase().trim();
        
        switch (type) {
            case "credit":
            case "creditcard":
            case "credit card":
                return new CreditCardPayment();
                
            case "paypal":
            case "pay pal":
                return new PayPalPayment();
                
            case "cash":
            case "tiền mặt":
            case "tien mat":
                return new CashPayment();
                
            default:
                throw new IllegalArgumentException("Loại thanh toán không hợp lệ: " + paymentType + 
                                                 "\nCác loại hỗ trợ: credit, paypal, cash");
        }
    }
}