package com.ssd.synchronizer.Model;

import java.util.HashMap;

public class UserSession {

    private static HashMap<String,CSRF> sessionData =new HashMap<>();

    public static void addSessionData(String username, CSRF csrfSession){

        sessionData.put(username,csrfSession);
    }

    public static HashMap<String, CSRF> getSessionData() {
        return sessionData;
    }

    public static void setSessionData(HashMap<String, CSRF> sessionData) {
        UserSession.sessionData = sessionData;
    }


}
