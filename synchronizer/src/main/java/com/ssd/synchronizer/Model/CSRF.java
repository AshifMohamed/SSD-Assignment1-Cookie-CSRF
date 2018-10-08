package com.ssd.synchronizer.Model;

public class CSRF {

    private String csrfToken;
    private String sessionToken;

    public CSRF(String csrfToken, String sessionToken) {
        this.csrfToken = csrfToken;
        this.sessionToken = sessionToken;
    }

    public String getCsrfToken() {
        return csrfToken;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrfToken = csrfToken;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "CSRF{" +
                "csrfToken='" + csrfToken + '\'' +
                ", sessionToken='" + sessionToken + '\'' +
                '}';
    }
}
