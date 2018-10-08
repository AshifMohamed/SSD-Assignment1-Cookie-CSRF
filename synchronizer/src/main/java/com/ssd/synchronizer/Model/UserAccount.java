package com.ssd.synchronizer.Model;

public class UserAccount {
    private String email;
    private String comments;
    private String csrfToken;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "email='" + email + '\'' +
                ", comments='" + comments + '\'' +
                ", csrfToken='" + csrfToken + '\'' +
                '}';
    }
}
