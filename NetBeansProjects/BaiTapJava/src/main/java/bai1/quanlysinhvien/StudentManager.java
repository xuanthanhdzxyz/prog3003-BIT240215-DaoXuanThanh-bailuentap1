package bai1.quanlysinhvien;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {
    private ArrayList<Student> students;
    private Scanner scanner;
    
    public StudentManager() {
        students = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    
    public void addStudent() {
        System.out.println("\n--- THÊM SINH VIÊN MỚI ---");
        System.out.print("Nhập MSSV: ");
        String id = scanner.nextLine();
        
        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();
        
        System.out.print("Nhập GPA: ");
        double gpa = Double.parseDouble(scanner.nextLine());
        
        Student student = new Student(id, name, gpa);
        students.add(student);
        System.out.println("Đã thêm sinh viên thành công!");
    }
    
    public void displayAllStudents() {
        System.out.println("\n--- DANH SÁCH SINH VIÊN ---");
        if (students.isEmpty()) {
            System.out.println("Danh sách sinh viên trống!");
        } else {
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i));
            }
        }
    }
    
    public void searchStudentByName() {
        System.out.println("\n--- TÌM KIẾM SINH VIÊN ---");
        System.out.print("Nhập tên cần tìm: ");
        String searchName = scanner.nextLine().toLowerCase();
        
        boolean found = false;
        for (Student student : students) {
            if (student.getName().toLowerCase().contains(searchName)) {
                System.out.println("Tìm thấy: " + student);
                found = true;
            }
        }
        
        if (!found) {
            System.out.println("Không tìm thấy sinh viên nào với tên: " + searchName);
        }
    }
    
    public void deleteStudentById() {
        System.out.println("\n--- XÓA SINH VIÊN ---");
        System.out.print("Nhập MSSV cần xóa: ");
        String id = scanner.nextLine();
        
        boolean found = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id)) {
                students.remove(i);
                System.out.println("Đã xóa sinh viên có MSSV: " + id);
                found = true;
                break;
            }
        }
        
        if (!found) {
            System.out.println("Không tìm thấy sinh viên với MSSV: " + id);
        }
    }
    
    public void showMenu() {
        int choice;
        do {
            System.out.println("\n=== QUẢN LÝ SINH VIÊN ===");
            System.out.println("1. Thêm sinh viên mới");
            System.out.println("2. Hiển thị danh sách sinh viên");
            System.out.println("3. Tìm kiếm sinh viên theo tên");
            System.out.println("4. Xóa sinh viên theo MSSV");
            System.out.println("5. Thoát");
            System.out.print("Chọn chức năng (1-5): ");
            
            choice = Integer.parseInt(scanner.nextLine());
            
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    searchStudentByName();
                    break;
                case 4:
                    deleteStudentById();
                    break;
                case 5:
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! Vui lòng chọn từ 1-5.");
            }
        } while (choice != 5);
    }
    
    public static void main(String[] args) {
        StudentManager manager = new StudentManager();
        manager.showMenu();
    }
}