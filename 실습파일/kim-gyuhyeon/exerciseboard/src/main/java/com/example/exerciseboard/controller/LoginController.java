// package com.example.exerciseboard.controller;

// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// @Controller
// public class LoginController {
    
//     @GetMapping("/login")
//     public String showLoginForm(@RequestParam(value = "error", required = false) String error,
//                                 @RequestParam(value = "logout", required = false) String logout,
//                                 Model model) {
//         if (error != null) {
//             model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
//         }
//         if (logout != null) {
//             model.addAttribute("successMessage", "로그아웃 되었습니다.");
//         }
//         return "login";
//     }
// }
