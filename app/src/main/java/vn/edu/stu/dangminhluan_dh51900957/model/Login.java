package vn.edu.stu.dangminhluan_dh51900957.model;

public class Login {
    private String email;
    private String pass;
    private boolean result;

    public Login() {
    }

    public Login(String email, String pass, boolean result) {
        this.email = email;
        this.pass = pass;
        this.result = result;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
}
