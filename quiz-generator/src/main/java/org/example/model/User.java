package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userName;
    private String password;
    private String email;

    public User(String userName, String password, String email, List<String> quizTakenByUser,Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.quizTakenByUser = quizTakenByUser;
        this.role = role;
    }

    private Role role;
    private List<String> quizTakenByUser = new ArrayList<>();

    public User(String userName, String password, String email, List<String> quizTakenByUser) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.quizTakenByUser = quizTakenByUser;
    }

    public boolean validatePassword(String password){
        return this.password.equals(password);
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getQuizTakenByUser() {
        return quizTakenByUser;
    }

    public void setQuizTakenByUser(List<String> quizTakenByUser) {
        this.quizTakenByUser = quizTakenByUser;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
