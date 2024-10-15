// package com.example.exerciseboard.controller;

// import com.example.exerciseboard.model.User;
// import com.example.exerciseboard.repository.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.*;
// import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// @Controller
// public class RegisterController {
    
//     @Autowired
//     private UserRepository userRepository;
    
//     @Autowired
//     private PasswordEncoder passwordEncoder;
    
//     @GetMapping("/register")
//     public String showRegistrationForm(Model model) {
//         model.addAttribute("user", new User());
//         return "register";
//     }
    
//     @PostMapping("/register")
//     public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
//         if(userRepository.findByUsername(user.getUsername()) != null) {
//             redirectAttributes.addFlashAttribute("errorMessage", "이미 존재하는 아이디입니다.");
//             return "redirect:/register";
//         }
//         user.setPassword(passwordEncoder.encode(user.getPassword()));
//         userRepository.save(user);
//         redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료되었습니다.");
//         return "redirect:/login";
//     }
// }
