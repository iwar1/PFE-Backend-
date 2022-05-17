package com.example.transcriboot.model;


public class Login {
      String emailctrl;
      String passwordctrl;

    public Login(String email, String password) {
        this.emailctrl = email;
        this.passwordctrl = password;
    }

    public String getEmail() {
        return emailctrl;
    }

    public void setEmail(String email) {
        this.emailctrl = email;
    }

    public String getPassword() {
        return passwordctrl;
    }

    public void setPassword(String password) {
        this.passwordctrl = password;
    }
}
