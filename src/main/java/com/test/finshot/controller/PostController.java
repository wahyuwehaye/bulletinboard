package com.test.finshot.controller;

import com.test.finshot.domain.Post;
import com.test.finshot.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PostController {

    @Autowired
    private PostService postService;
    
    @GetMapping({"/", "/posts"})
    public String listPosts(Model model) {
        model.addAttribute("posts", postService.getAllPosts());
        return "list";
    }
    
    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Post post = postService.getPost(id);
        if (post == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Post not found.");
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "view";
    }
    
    @GetMapping("/posts/new")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }
    
    @PostMapping("/posts")
    public String createPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        if (postService.createPost(post)) {
            redirectAttributes.addFlashAttribute("message", "Post created successfully.");
            return "redirect:/posts";
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Error creating post.");
            return "redirect:/posts/new";
        }
    }
    
    @GetMapping("/posts/{id}/edit")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {
        Post post = postService.getPost(id);
        if (post == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Post not found.");
            return "redirect:/posts";
        }
        model.addAttribute("post", post);
        return "edit";
    }
    
    @PostMapping("/posts/{id}/update")
    public String updatePost(@PathVariable("id") Integer id,
                             @RequestParam("password") String password,
                             @ModelAttribute Post post,
                             RedirectAttributes redirectAttributes) {
        post.setId(id);
        if (postService.updatePost(post, password)) {
            redirectAttributes.addFlashAttribute("message", "Post updated successfully.");
            return "redirect:/posts/" + id;
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid password or update error.");
            return "redirect:/posts/" + id + "/edit";
        }
    }
    
    @PostMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable("id") Integer id,
                             @RequestParam("password") String password,
                             RedirectAttributes redirectAttributes) {
        if (postService.deletePost(id, password)) {
            redirectAttributes.addFlashAttribute("message", "Post deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid password or delete error.");
        }
        return "redirect:/posts";
    }
}
