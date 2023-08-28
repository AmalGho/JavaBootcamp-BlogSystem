package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.Blog;
import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.BlogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/blog")
public class BlogController {
    private final BlogService blogService;

    @GetMapping("/getAll")
    public ResponseEntity getAllBlogs() {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getAllBlogs());
    }

    @PostMapping("/add")
    public ResponseEntity addBlog(@AuthenticationPrincipal User user, @RequestBody @Valid Blog blog) {
        blogService.addBlog(user.getId(), blog);
        return ResponseEntity.status(HttpStatus.OK).body("blog added successfully");
    }

    @PutMapping("/update/{blog_id}")
    public ResponseEntity updateBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id, @RequestBody @Valid Blog blog) {
        blogService.updateBlog(user.getId(), blog_id, blog);
        return ResponseEntity.status(HttpStatus.OK).body("blog updated successfully");
    }

    @DeleteMapping("/delete/{blog_id}")
    public ResponseEntity deleteBlog(@AuthenticationPrincipal User user, @PathVariable Integer blog_id) {
        blogService.deleteBlog(user.getId(), blog_id);
        return ResponseEntity.status(HttpStatus.OK).body("blog deleted successfully");
    }

    @GetMapping("/getUserBlogs")
    public ResponseEntity getUserBlogs(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getUserBlogs(user.getId()));
    }


    @GetMapping("/getById/{blog_id}")
    public ResponseEntity getBlogById(@AuthenticationPrincipal User user, @PathVariable Integer blog_id) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogById(user.getId(), blog_id));
    }

    @GetMapping("/getByTitle/{blog_title}")
    public ResponseEntity getBlogByTitle(@AuthenticationPrincipal User user, @PathVariable String blog_title) {
        return ResponseEntity.status(HttpStatus.OK).body(blogService.getBlogByTitle(user.getId(), blog_title));
    }
}
