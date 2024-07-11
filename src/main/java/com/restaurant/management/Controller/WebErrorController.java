package com.restaurant.management.Controller;


import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class WebErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            if (statusCode == HttpStatus.FORBIDDEN.value()) {
                model.addAttribute("errorMessage", "Access Denied!");
                return "error/access-denied";
            } else if (statusCode == HttpStatus.UNAUTHORIZED.value()) {
                model.addAttribute("errorMessage", "Unauthorized!");
                return "error/unauthorized";
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "The requested page could not be found.");
                return "error/not-found";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMessage", "Something went wrong on our end.");
                return "error/internal-server-error";
            }
            
        }
        model.addAttribute("errorMessage", "An unknown error has occurred.");
        return "error/default";
    }

    public String getErrorPath() {
        return "/error";
    }
}


