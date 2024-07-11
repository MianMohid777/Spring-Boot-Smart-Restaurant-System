package com.restaurant.management.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itextpdf.text.pdf.PdfStructTreeController.returnType;
import com.restaurant.management.Entity.*;
import com.restaurant.management.Repository.ManagerRepository;
import com.restaurant.management.Repository.OwnerRepository;

import ch.qos.logback.core.joran.conditional.ElseAction;

@Controller
public class LoginController 
{
    
    @Autowired
    ManagerRepository managerRepo;
    
    @Autowired
    OwnerRepository ownerRepo;

    

    public LoginController(ManagerRepository managerRepo, OwnerRepository ownerRepo) {
        this.managerRepo = managerRepo;
        this.ownerRepo = ownerRepo;
    }

    @RequestMapping("/login")
    public String loginForm()
    {
        return "login";
    }

    @PostMapping("/login/auth")
    public String verify(@RequestParam("username") String username,@RequestParam("password") String password, Model model)
    {
        Manager m = managerRepo.findByUserName(username);
        Owner o = ownerRepo.findByUserName(username);

        if(o!=null)
        {
            System.out.println("\n\n" + username);
        }
        if(username.equals("admin") && password.equals("admin123"))
        {
            return "admin-dash";
        }
        else if(m != null && m.getUserName().equals(username) & m.getPassWord().equals(password))
        {
            System.out.println("\n\n" + m.getName());
         return "manager-dash";
        }
        else if(o != null && o.getUserName().equals(username) && o.getPassWord().equals(password) )
        {
            System.out.println("\n\n" + o.getName());
        return "owner-dash";
        }

        model.addAttribute("error","Invalid Email or Password !!!");

        return "login";
    }
}
