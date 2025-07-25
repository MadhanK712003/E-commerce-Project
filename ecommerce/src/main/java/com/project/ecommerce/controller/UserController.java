package com.project.ecommerce.controller;


import com.project.ecommerce.model.User;
import com.project.ecommerce.repository.UserRepository;
import com.project.ecommerce.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }


    @GetMapping("/register")
    public String showFrom(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String saveUsers(@Valid @ModelAttribute("user") User user,
                            BindingResult bindingResult,
                            RedirectAttributes attributes) {

        if(userRepository.existsByUsername(user.getUsername())){
            bindingResult.rejectValue("username","error.user","Oops! That username is already taken. Try a different one.");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        if (user.getPassword().equals(user.getConfirm_Password())) {
            userService.save(user);
            attributes.addFlashAttribute("success", "Registered successfully!");
            return "redirect:/login";
        } else {
            bindingResult.rejectValue("confirm_Password", "error.user", "Password does not match");
            return "register";
        }
    }

    @GetMapping("/login")
    public String loginPage(Model model,
                            @RequestParam(value = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Invalid username or password");
        }
        return "login";
    }




//    @GetMapping("/updateUser")
//    public String update(@RequestParam("userId") Long id,Model model){
//        User user = userService.findById(id);
//        if (user != null){
//            model.addAttribute("user", user);
//            return "update-user";
//        }
//        return "redirect:/home";
//    }
//
//    @PostMapping("/updateForm")
//    public String updateUsers(@Valid @ModelAttribute("user") User user,
//                              BindingResult bindingResult,
//                              RedirectAttributes attributes){
//        if (bindingResult.hasErrors()){
//            return "update-user";
//        }
//        if (user.getPassword().equals(user.getConfirm_Password())) {
//            userService.save(user);
//            attributes.addFlashAttribute("success", "Registered successfully!");
//            return "redirect:/home";
//        }else {
//            bindingResult.rejectValue("confirm_Password","error.user", "Password does not match");
//            return "update-user";
//        }
//    }
//
//
//    @PostMapping("/deleteUser")
//    public String delete(@RequestParam("userId") Long theId, Principal principal,User use, HttpServletRequest request) throws ServletException {
//        String username = principal.getName();
//        User user = userService.findByUsername(username);
//        if (user != null){
//            Long userId = user.getId();
//            userService.deleteById(userId);
//            request.logout();
//            return "redirect:/login?accountDeleted";
//        }
//        return "redirect:/home?error=userNotFound";
//
//    }

//    @GetMapping("/logout")
//    public String logout(Model model){
//        model.addAttribute("logoutMsg","Logout Successfully!");
//        return "login";
//    }

}
