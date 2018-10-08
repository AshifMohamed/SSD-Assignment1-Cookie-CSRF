package com.ssd.doublesubmit.Model;

import java.util.HashMap;

public class UserSession {

    private static HashMap<String,String> sessionData =new HashMap<>();

    public static void addSessionData(String username, String sessionId){

        sessionData.put(username,sessionId);
    }

    public static HashMap<String, String> getSessionData() {
        return sessionData;
    }

    public static void setSessionData(HashMap<String, String> sessionData) {
        UserSession.sessionData = sessionData;
    }
}
