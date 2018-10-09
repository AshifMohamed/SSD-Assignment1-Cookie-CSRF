package com.ssd.synchronizer.Service;

import com.ssd.synchronizer.Model.CSRF;
import com.ssd.synchronizer.Model.User;
import com.ssd.synchronizer.Model.UserSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.Map;

@Service
public class SynchronizerService {

    private final String SESSION_COOKIE_NAME = "sessionId";

    public String getCSRFServerToken(){

        for (Map.Entry<String, CSRF> sessionData : UserSession.getSessionData().entrySet()) {

            if (sessionData.getKey().equals(User.currentUser)) {

                return sessionData.getValue().getCsrfToken();
            }
        }
        return null;
    }
    /**
     * Validate a request by verifying the Session of the current user and CSRF tokens
     * @param cookies
     * @param userCSRF
     * @return
     */
    public boolean authenticateRequest(Cookie[] cookies, String userCSRF) {

        String cookieSession=null;

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals(SESSION_COOKIE_NAME)) {
                cookieSession = cookie.getValue();

            }
        }

        return (authenticateUser(cookieSession) && verifyCSRFToken(userCSRF));
    }

    /**
     * Verify the Session of the current user
     * @param sessionId
     * @return
     */
    private boolean authenticateUser(String sessionId) {

        return sessionId.equals(getUserSessionId());

    }

    /**
     * Get the SessionId of the current user
     * @return
     */
    private String getUserSessionId() {

        for (Map.Entry<String, CSRF> sessionData : UserSession.getSessionData().entrySet()) {

            if (sessionData.getKey().equals(User.currentUser)) {

                return sessionData.getValue().getSessionToken();
            }
        }
        return null;
    }


    /**
     * verify whether the two CSRF tokens are equal
     * @param submitCSRF
     * @return
     */
    private boolean verifyCSRFToken(String submitCSRF) {

        return submitCSRF.equals(getUserCSRF());
    }

    /**
     * Get the CSRF Token of the current user
     * @return
     */
    private String getUserCSRF() {

        for (Map.Entry<String, CSRF> sessionData : UserSession.getSessionData().entrySet()) {

            if (sessionData.getKey().equals(User.currentUser)) {

                return sessionData.getValue().getCsrfToken();
            }
        }
        return null;
    }

}
