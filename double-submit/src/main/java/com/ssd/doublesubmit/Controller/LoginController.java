package com.ssd.doublesubmit.Controller;

import com.ssd.doublesubmit.Model.User;
import com.ssd.doublesubmit.Model.UserAccount;
import com.ssd.doublesubmit.Service.DoubleSubmitService;
import com.ssd.doublesubmit.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.resource.HttpResource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * Show login page
     * @param model
     * @param status
     * @return
     */
    @GetMapping("/")
    public String loginPage(Model model, @ModelAttribute("Status") String status) {

        model.addAttribute("Status", status+"");
        model.addAttribute("user", new User());
        return "login";
    }

    /**
     * Check login credentials validitity and redirect user to home page
     * @param user
     * @param response
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public String doubleSubmitLogin(@ModelAttribute User user, HttpServletResponse response, RedirectAttributes redirectAttributes){


        if(loginService.authenticateUser(user.getUsername(),user.getPassword()) ){

            Cookie cookieSessionId = new Cookie("sessionId", loginService.saveSessionData(user.getUsername()));
            Cookie cookieCSRF      = new Cookie("csrfCookie", loginService.generateRandomToken());

            response.addCookie(cookieSessionId);
            response.addCookie(cookieCSRF);

            return "redirect:/account";

        }else{

            redirectAttributes.addFlashAttribute("Status", "error");
            return "redirect:/";

        }
    }

    /**
     * show home page
     * @param model
     * @param status
     * @return
     */
    @GetMapping("/account")
    public String homePage(Model model, @ModelAttribute("Status") String status){

        model.addAttribute("Status", status+"");
        model.addAttribute("account", new UserAccount());
        return "home";
    }
}
