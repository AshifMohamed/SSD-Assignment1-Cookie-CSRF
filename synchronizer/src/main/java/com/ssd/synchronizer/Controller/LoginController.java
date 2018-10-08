package com.ssd.synchronizer.Controller;

import com.ssd.synchronizer.Model.User;
import com.ssd.synchronizer.Model.UserAccount;
import com.ssd.synchronizer.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
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
     * Validate login credentials and redirect user to home page.
     * If user is successfully validated, redirect to home page.
     * Else redirect user with an Error Status
     * @param user
     * @param response
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/login")
    public String doubleSubmitLogin(@ModelAttribute User user, HttpServletResponse response, RedirectAttributes redirectAttributes){


        if(loginService.authenticateUser(user.getUsername(),user.getPassword()) ){
            Cookie cookieSessionId = new Cookie("sessionId", loginService.saveSessionData(user.getUsername()));
            response.addCookie(cookieSessionId);
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
