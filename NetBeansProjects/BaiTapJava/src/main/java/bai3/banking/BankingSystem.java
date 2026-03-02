package bai3.banking;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class BankingSystem {
    
    // Giả lập cơ sở dữ liệu tài khoản
    private static BankAccount senderAccount = new BankAccount("ACC001", "Nguyễn Văn A", 5000.0);
    private static BankAccount receiverAccount = new BankAccount("ACC002", "Trần Thị B", 1000.0);
    private static Random random = new Random();
    
    // Tác vụ 1: Xác thực thông tin khách hàng
    private static CompletableFuture<Boolean> authenticateCustomer(String accountNumber, String customerName) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🔄 [1/3] Đang xác thực thông tin khách hàng...");
                TimeUnit.SECONDS.sleep(2); // Giả lập độ trễ 2 giây
                
                // Giả lập kiểm tra thông tin
                if (accountNumber == null || accountNumber.isEmpty()) {
                    throw new RuntimeException("Số tài khoản không hợp lệ!");
                }
                
                if (customerName == null || customerName.isEmpty()) {
                    throw new RuntimeException("Tên khách hàng không hợp lệ!");
                }
                
                // Giả lập xác thực thất bại (10% tỷ lệ thất bại)
                if (random.nextInt(100) < 10) {
                    throw new RuntimeException("Xác thực thất bại: Thông tin không khớp với hệ thống!");
                }
                
                System.out.println("✅ [1/3] Xác thực thành công cho khách hàng: " + customerName);
                return true;
                
            } catch (InterruptedException e) {
                throw new RuntimeException("Lỗi hệ thống trong quá trình xác thực!");
            }
        });
    }
    
    // Tác vụ 2: Kiểm tra số dư tài khoản
    private static CompletableFuture<Boolean> checkBalance(String accountNumber, double amount) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🔄 [2/3] Đang kiểm tra số dư tài khoản...");
                TimeUnit.SECONDS.sleep(1); // Giả lập độ trễ 1 giây
                
                if (amount <= 0) {
                    throw new RuntimeException("Số tiền giao dịch phải lớn hơn 0!");
                }
                
                if (amount > 10000) {
                    throw new RuntimeException("Số tiền giao dịch vượt quá hạn mức cho phép ($10,000)!");
                }
                
                if (senderAccount.getBalance() < amount) {
                    throw new RuntimeException("Số dư không đủ! Số dư hiện tại: $" + 
                                             senderAccount.getBalance() + ", Cần: $" + amount);
                }
                
                System.out.println("✅ [2/3] Kiểm tra số dư thành công. Số dư khả dụng: $" + 
                                 senderAccount.getBalance());
                return true;
                
            } catch (InterruptedException e) {
                throw new RuntimeException("Lỗi hệ thống trong quá trình kiểm tra số dư!");
            }
        });
    }
    
    // Tác vụ 3: Thực hiện giao dịch chuyển tiền
    private static CompletableFuture<TransactionResult> processTransaction(
            String fromAccount, String toAccount, double amount) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                System.out.println("🔄 [3/3] Đang thực hiện giao dịch chuyển tiền...");
                TimeUnit.SECONDS.sleep(3); // Giả lập độ trễ 3 giây
                
                // Thực hiện chuyển tiền
                double oldBalance = senderAccount.getBalance();
                senderAccount.withdraw(amount);
                receiverAccount.deposit(amount);
                
                String message = String.format("Đã chuyển $%.2f từ %s đến %s. " +
                                              "Số dư cũ: $%.2f, Số dư mới: $%.2f",
                                              amount, fromAccount, toAccount, 
                                              oldBalance, senderAccount.getBalance());
                
                System.out.println("✅ [3/3] Giao dịch hoàn tất!");
                
                return new TransactionResult(true, message, amount, 
                                           fromAccount, toAccount, senderAccount.getBalance());
                
            } catch (InterruptedException e) {
                throw new RuntimeException("Lỗi hệ thống trong quá trình xử lý giao dịch!");
            }
        });
    }
    
    // Phương thức chính để thực hiện giao dịch
    public static CompletableFuture<TransactionResult> transferMoney(
            String fromAccount, String fromName, String toAccount, double amount) {
        
        System.out.println("\n🔄 BẮT ĐẦU GIAO DỊCH CHUYỂN TIỀN");
        System.out.println("   Từ: " + fromAccount + " - " + fromName);
        System.out.println("   Đến: " + toAccount);
        System.out.println("   Số tiền: $" + amount);
        System.out.println("   Thời gian: " + java.time.LocalTime.now());
        System.out.println("----------------------------------------");
        
        // Thực hiện các tác vụ bất đồng bộ theo chuỗi
        return authenticateCustomer(fromAccount, fromName)
            .thenCompose(authenticated -> {
                if (authenticated) {
                    return checkBalance(fromAccount, amount);
                } else {
                    throw new RuntimeException("Không thể tiếp tục giao dịch do xác thực thất bại!");
                }
            })
            .thenCompose(balanceChecked -> {
                if (balanceChecked) {
                    return processTransaction(fromAccount, toAccount, amount);
                } else {
                    throw new RuntimeException("Không thể tiếp tục giao dịch do kiểm tra số dư thất bại!");
                }
            })
            .exceptionally(ex -> {
                // Xử lý lỗi
                String errorMessage = "Giao dịch thất bại: " + ex.getMessage();
                System.err.println("❌ " + errorMessage);
                return new TransactionResult(false, errorMessage, amount, 
                                           fromAccount, toAccount, senderAccount.getBalance());
            });
    }
    
    // Hiển thị thông tin tài khoản
    private static void displayAccounts() {
        System.out.println("\n📊 TRẠNG THÁI TÀI KHOẢN HIỆN TẠI:");
        System.out.println("   " + senderAccount);
        System.out.println("   " + receiverAccount);
    }
    
    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("   HỆ THỐNG GIAO DỊCH NGÂN HÀNG BẤT ĐỒNG BỘ");
        System.out.println("=========================================");
        
        displayAccounts();
        
        // Ví dụ 1: Giao dịch thành công
        System.out.println("\n📌 VÍ DỤ 1: GIAO DỊCH THÀNH CÔNG");
        CompletableFuture<TransactionResult> transaction1 = transferMoney(
            "ACC001", "Nguyễn Văn A", "ACC002", 500.0
        );
        
        // Chờ và hiển thị kết quả
        transaction1.thenAccept(result -> {
            System.out.println("\n📋 KẾT QUẢ GIAO DỊCH 1:");
            System.out.println(result);
            displayAccounts();
        }).join();
        
        // Ví dụ 2: Giao dịch thất bại do số dư không đủ
        System.out.println("\n📌 VÍ DỤ 2: GIAO DỊCH THẤT BẠI (SỐ DƯ KHÔNG ĐỦ)");
        CompletableFuture<TransactionResult> transaction2 = transferMoney(
            "ACC001", "Nguyễn Văn A", "ACC002", 10000.0
        );
        
        transaction2.thenAccept(result -> {
            System.out.println("\n📋 KẾT QUẢ GIAO DỊCH 2:");
            System.out.println(result);
            displayAccounts();
        }).join();
        
        // Ví dụ 3: Giao dịch thất bại do thông tin không hợp lệ
        System.out.println("\n📌 VÍ DỤ 3: GIAO DỊCH THẤT BẠI (THÔNG TIN KHÔNG HỢP LỆ)");
        CompletableFuture<TransactionResult> transaction3 = transferMoney(
            "ACC999", "Người Lạ", "ACC002", 100.0
        );
        
        transaction3.thenAccept(result -> {
            System.out.println("\n📋 KẾT QUẢ GIAO DỊCH 3:");
            System.out.println(result);
            displayAccounts();
        }).join();
        
        // Ví dụ 4: Thực hiện nhiều giao dịch đồng thời
        System.out.println("\n📌 VÍ DỤ 4: THỰC HIỆN NHIỀU GIAO DỊCH ĐỒNG THỜI");
        
        CompletableFuture<TransactionResult> transA = transferMoney(
            "ACC001", "Nguyễn Văn A", "ACC002", 200.0
        );
        
        CompletableFuture<TransactionResult> transB = transferMoney(
            "ACC001", "Nguyễn Văn A", "ACC002", 300.0
        );
        
        // Chờ tất cả giao dịch hoàn thành
        CompletableFuture.allOf(transA, transB).join();
        
        System.out.println("\n📊 TẤT CẢ GIAO DỊCH ĐÃ HOÀN TẤT:");
        displayAccounts();
        
        System.out.println("\n=========================================");
        System.out.println("   CẢM ƠN BẠN ĐÃ SỬ DỤNG DỊCH VỤ NGÂN HÀNG!");
        System.out.println("=========================================");
    }
}