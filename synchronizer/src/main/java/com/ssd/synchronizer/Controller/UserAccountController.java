package com.ssd.synchronizer.Controller;

import com.ssd.synchronizer.Model.UserAccount;
import com.ssd.synchronizer.Service.SynchronizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserAccountController {

    @Autowired
    SynchronizerService synchronizerService;

    /**
     * check request validity through CSRF token.
     * If the CSRF token submit through form matches the CSRF in Server, return success Status.
     * Else Return error Status
     * @param userAccount
     * @param request
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/change-email")
    public String changeEmail(@ModelAttribute UserAccount userAccount, HttpServletRequest request, RedirectAttributes redirectAttributes){

        if (synchronizerService.authenticateRequest(request.getCookies(),userAccount.getCsrfToken())){

            redirectAttributes.addFlashAttribute("Status","success");
            return "redirect:/account";

        }else{

            redirectAttributes.addFlashAttribute("Status","error");
            return "redirect:/account";
        }
    }
}
