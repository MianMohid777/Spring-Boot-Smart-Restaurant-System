package com.restaurant.management.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.restaurant.management.Entity.*;
import com.restaurant.management.Services.AdminServices;


@Controller
public class AdminController {
    
    
    @Autowired
    private AdminServices adminService;


    public AdminController(AdminServices adminService) {
        super();
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String admin_Dashboard()
    {
        return "admin-dash";
    }

/////////////////////////////////////////////////////////////////////////////////////////////
   
// Manager //

    @GetMapping("/manager")
    public String manage_Manager(Model model)
    {
        List<Manager> manager = adminService.getAllManagers();
        model.addAttribute("managers", manager);
       return "manager";
    }


    @GetMapping("/manager/create/new")
    public String createManager(Model model)
    {
        Manager M = new Manager();

        model.addAttribute("manager", M);
        return "add-manager";
        
    }

    @PostMapping("/admin/add")
    public String saveManager( @ModelAttribute("manager") Manager manager,Model model)
    {
        if(adminService.getManagerByUsername(manager.getUserName()) == null)
        {
           adminService.addManager(manager);
        }
        else
        {
            model.addAttribute("error", "Username already taken !");
            return "add-manager";
        }
        
        return "redirect:/manager";
    }

    @GetMapping("/manager/update/{id}")
    public String updateManager(Model model,@PathVariable Long id)
    {
        Manager m = adminService.getManagerById(id);
        model.addAttribute("manager", m);
        return "update-manager";
    }

    @PostMapping("/admin/update/{id}")
    public String saveUpdatedManager(@PathVariable Long id,@ModelAttribute("manager") Manager manager)
    {
        Manager updated = new Manager();

        updated.setId(id);
        updated.setName(manager.getName());
        updated.setUserName(manager.getUserName());
        updated.setPhoneNo(manager.getPhoneNo());
        updated.setPassWord(manager.getPassWord());

        adminService.updateManager(updated);
        return "redirect:/manager";
    }

    @GetMapping("/manager/delete/{id}")
    public String deleteManger(@PathVariable Long id,Model model)
    {
        Manager m = adminService.getManagerById(id);

        model.addAttribute("manager", m);
        //adminService.deleteManager(id);
        return "delete-manager";
    }

    @PostMapping("/admin/delete/{id}")
    public String deleteConfirmed(@RequestParam Long id, @RequestParam boolean confirm) {
    if (confirm) {
        adminService.deleteManager(id);
    }
    return "redirect:/manager";
}


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


// Owner //



@GetMapping("/owner")
    public String manage_Owner(Model model)
    {
        List<Owner> owner = adminService.getAllOwner();
        model.addAttribute("owners", owner);
       return "owner";
    }


    @GetMapping("owner/create/new")
    public String createOwner(Model model)
    {
        Owner O = new Owner();

        model.addAttribute("owner", O);
        return "add-owner";
        
    }

    @PostMapping("/admin/add-owner")
    public String saveOwner( @ModelAttribute("owner") Owner owner,Model model)
    {
        if(adminService.getManagerByUsername(owner.getUserName()) == null)
        {
   
          adminService.addOwner(owner);
        }
        else
        {
            model.addAttribute("error", "Username already taken !");
            return "add-owner";
        }

        
        return "redirect:/owner";
    }

    @GetMapping("/owner/update/{id}")
    public String updateOwner(Model model,@PathVariable Long id)
    {
        Owner o = adminService.getOwnerById(id);
        model.addAttribute("owner", o);
        return "update-owner";
    }

    @PostMapping("/admin/update-owner/{id}")
    public String saveUpdatedOwner(@PathVariable Long id,@ModelAttribute("owner") Owner owner)
    {
        Owner updated = new Owner();

        updated.setId(id);
        updated.setName(owner.getName());
        updated.setUserName(owner.getUserName());
        updated.setPhoneNo(owner.getPhoneNo());
        updated.setPassWord(owner.getPassWord());

        adminService.updateOwner(updated);
        return "redirect:/owner";
    }

    @GetMapping("/owner/delete/{id}")
    public String deleteOwner(@PathVariable Long id,Model model)
    {
        Owner o = adminService.getOwnerById(id);

        model.addAttribute("owner", o);
        return "delete-owner";
    }

    @PostMapping("/admin/delete-owner/{id}")
    public String deleteConfirmed_Owner(@RequestParam Long id, @RequestParam boolean confirm) {
    if (confirm) {
        adminService.deleteOwner(id);
    }
    return "redirect:/owner";
}
}
    


