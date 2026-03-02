package bai2.payment;

import java.util.Scanner;

public class PaymentSystem {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=================================");
        System.out.println("   HỆ THỐNG THANH TOÁN ĐA DẠNG");
        System.out.println("=================================");
        
        // Demo 1: Thanh toán bằng Credit Card
        System.out.println("\n📌 VÍ DỤ 1: Thanh toán tự động");
        Payment creditPayment = PaymentFactory.createPayment("credit");
        creditPayment.processPayment(150.50);
        
        // Demo 2: Thanh toán bằng PayPal
        System.out.println("\n📌 VÍ DỤ 2: Thanh toán tự động");
        Payment paypalPayment = PaymentFactory.createPayment("paypal");
        paypalPayment.processPayment(75.25);
        
        // Demo 3: Thanh toán bằng tiền mặt
        System.out.println("\n📌 VÍ DỤ 3: Thanh toán tự động");
        Payment cashPayment = PaymentFactory.createPayment("cash");
        cashPayment.processPayment(200.00);
        
        // Demo 4: Người dùng nhập lựa chọn
        System.out.println("\n=================================");
        System.out.println("   THANH TOÁN THEO LỰA CHỌN");
        System.out.println("=================================");
        
        System.out.println("Chọn phương thức thanh toán:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Tiền mặt");
        System.out.print("Nhập lựa chọn (1-3): ");
        
        int choice = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập số tiền cần thanh toán: $");
        double amount = Double.parseDouble(scanner.nextLine());
        
        Payment userPayment = null;
        
        switch (choice) {
            case 1:
                userPayment = PaymentFactory.createPayment("credit");
                break;
            case 2:
                userPayment = PaymentFactory.createPayment("paypal");
                break;
            case 3:
                userPayment = PaymentFactory.createPayment("cash");
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        
        System.out.println();
        userPayment.processPayment(amount);
        
        // Demo 5: Xử lý lỗi
        System.out.println("\n=================================");
        System.out.println("   XỬ LÝ LỖI THANH TOÁN");
        System.out.println("=================================");
        
        try {
            System.out.println("Thử thanh toán với loại không hợp lệ...");
            Payment invalidPayment = PaymentFactory.createPayment("bitcoin");
            invalidPayment.processPayment(100);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Lỗi: " + e.getMessage());
        }
        
        System.out.println("\n=================================");
        System.out.println("   CẢM ƠN BẠN ĐÃ SỬ DỤNG DỊCH VỤ!");
        System.out.println("=================================");
        
        scanner.close();
    }
}