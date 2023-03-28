package vn.edu.stu.dangminhluan_dh51900957.model;

public class Sinhvien {
    private String Ten;
    private String DTB;

    public Sinhvien() {
    }

    public Sinhvien(String ten, String DTB) {
        Ten = ten;
        this.DTB = DTB;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDTB() {
        return DTB;
    }

    public void setDTB(String DTB) {
        this.DTB = DTB;
    }

    @Override
    public String toString() {
        return "Sinhvien{" +
                "Ten='" + Ten + '\'' +
                ", DTB='" + DTB + '\'' +
                '}';
    }
}
