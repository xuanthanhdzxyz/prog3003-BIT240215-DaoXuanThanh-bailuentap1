package bai1.quanlysinhvien;

public class Student {
    private String id;
    private String name;
    private double gpa;
    
    public Student(String id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
    
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public double getGpa() {
        return gpa;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    
    @Override
    public String toString() {
        return "MSSV: " + id + ", Tên: " + name + ", GPA: " + gpa;
    }
}