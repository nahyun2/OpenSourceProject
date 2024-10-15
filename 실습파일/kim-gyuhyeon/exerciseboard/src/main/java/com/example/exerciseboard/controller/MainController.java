package com.example.exerciseboard.controller;

import com.example.exerciseboard.model.Post;
import com.example.exerciseboard.model.User;
import com.example.exerciseboard.repository.PostRepository;
import com.example.exerciseboard.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;

@Controller
public class MainController {
    
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;
    
    public MainController(UserRepository userRepository, PostRepository postRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/")
    public String index(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }
    
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "아이디 또는 비밀번호가 잘못되었습니다.");
        }
        if (logout != null) {
            model.addAttribute("successMessage", "로그아웃 되었습니다.");
        }
        return "login";
    }
    
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        if(userRepository.findByUsername(user.getUsername()) != null){
            redirectAttributes.addFlashAttribute("errorMessage", "이미 존재하는 아이디입니다.");
            return "redirect:/register";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "회원가입이 완료되었습니다. 로그인 해주세요.");
        return "redirect:/login";
    }
    
    @GetMapping("/board")
    public String board(Model model, Principal principal) {
        String currentUserLocation = getUserLocation(principal);
        List<Post> posts = postRepository.findByLocation(currentUserLocation);
        model.addAttribute("posts", posts);
        return "board";
    }
    
    @GetMapping("/board/create")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "create_post";
    }
    
    @PostMapping("/board/create")
    public String createPost(@ModelAttribute Post post, Principal principal, RedirectAttributes redirectAttributes) {
        if(principal != null){
            String username = principal.getName();
            User user = userRepository.findByUsername(username);
            if(user != null){
                post.setAuthor(user.getName());
            } else{
                post.setAuthor("익명");
            }
        } else{
            post.setAuthor("익명");
        }
        postRepository.save(post);
        redirectAttributes.addFlashAttribute("successMessage", "게시글이 작성되었습니다.");
        return "redirect:/";
    }
    
    private String getUserLocation(Principal principal){
        if(principal == null){
            return "서울";
        }
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        return user != null ? user.getLocation() : "서울";
    }
}
