package com.example.blogsystem.Controller;

import com.example.blogsystem.Model.User;
import com.example.blogsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid User user) {
        authService.register(user);
        return ResponseEntity.status(HttpStatus.OK).body("success registration");
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        return ResponseEntity.status(200).body("logout success");
    }
}
