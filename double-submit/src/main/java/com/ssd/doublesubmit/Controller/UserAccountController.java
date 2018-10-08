package com.ssd.doublesubmit.Controller;

import com.ssd.doublesubmit.Model.UserAccount;
import com.ssd.doublesubmit.Service.DoubleSubmitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserAccountController {

    @Autowired
    DoubleSubmitService doubleSubmitService;

    /**
     * check request validity through CSRF token return status
     * @param userAccount
     * @param request
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/change-email")
    public String changeEmail(@ModelAttribute UserAccount userAccount, HttpServletRequest request, RedirectAttributes redirectAttributes){

        if (doubleSubmitService.authenticateRequest(request.getCookies(),userAccount.getCsrfToken())){

            redirectAttributes.addFlashAttribute("Status","success");
            return "redirect:/account";

        }else{

            redirectAttributes.addFlashAttribute("Status","error");
            return "redirect:/account";
        }
    }
}
