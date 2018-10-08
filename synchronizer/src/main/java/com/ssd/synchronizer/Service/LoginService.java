package com.ssd.synchronizer.Service;

import com.ssd.synchronizer.Model.CSRF;
import com.ssd.synchronizer.Model.User;
import com.ssd.synchronizer.Model.UserSession;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginService {


    /**
     * This method is used to verify the logged user
     * @param username
     * @param password
     * @return
     */
    public boolean authenticateUser(String username, String password){

        return ((username.equalsIgnoreCase("synchUser")) && (password.equals("synchPass")));

    }

    /**
     * This method is used to retrieve a type 4 (pseudo randomly generated) UUID.
     * @return  a unique random number
     */
    public String generateRandomToken() {

        return UUID.randomUUID().toString();

    }

    /**
     * Generates a random number and store it as a cookie
     * @param
     * @return session identifier for the current session
     */
    public String saveSessionData(String username) {

        UserSession.getSessionData().remove(username);
        String sessionString = generateRandomToken();
        UserSession.addSessionData(username, new CSRF(generateRandomToken(),sessionString));
        User.currentUser = username;
        System.out.println("added Session Cookie");

        return sessionString;
    }

}
