package com.ssd.doublesubmit.Service;

import com.ssd.doublesubmit.Model.User;
import com.ssd.doublesubmit.Model.UserSession;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Service
public class DoubleSubmitService {

    private final String SESSION_COOKIE_NAME = "sessionId";
    private final String  CSRF_COOKIE_NAME  = "csrfCookie";

    /**
     * Validate a request by verifying the Session of the current user and CSRF tokens
     * @param cookies
     * @param userCSRF
     * @return
     */
    public boolean authenticateRequest(Cookie[] cookies, String userCSRF) {

        String cookieCSRF = null;
        String cookieSession=null;

        for (Cookie cookie : cookies) {

            if (cookie.getName().equals(SESSION_COOKIE_NAME)) {
                cookieSession = cookie.getValue();

            }else if (cookie.getName().equals(CSRF_COOKIE_NAME)) {
                cookieCSRF = cookie.getValue();
            }
        }

        return (authenticateUser(cookieSession) && verifyCSRFToken(cookieCSRF,userCSRF));
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

        for (Map.Entry<String, String> sessionData : UserSession.getSessionData().entrySet()) {

            if (sessionData.getKey().equals(User.currentUser)) {

                return sessionData.getValue();
            }
        }
        return null;
    }


    /**
     * verify whether the two CSRF tokens are equal
     * @param csrfCookie
     * @param userCSRF
     * @return
     */
    private boolean verifyCSRFToken(String csrfCookie, String userCSRF) {

        return userCSRF.equals(csrfCookie);
    }

}
