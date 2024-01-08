package shushi.auth.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.auth.dto.LoginUserDto;
import shushi.auth.dto.RegisterDto;
import shushi.auth.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/public/auth")
public class CartController {

    @Autowired
    private UserService userService;

    @PostMapping("/public/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUserDto loginUserDto)  {
        Map<String, Object> userDetail = userService.login(loginUserDto);
        if (userDetail != null) {
            return ResponseEntity.ok(userDetail);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PostMapping("/public/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody RegisterDto registerDto){
        Map<String, Object> userDetail = userService.register(registerDto);
        if (userDetail != null) {
            return ResponseEntity.ok(userDetail);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/user/info")
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        // Logic to retrieve user information (requires authentication)
        Map<String, Object> userInfo = userService.getUserInfo();
        if (userInfo != null) {
            return ResponseEntity.ok(userInfo);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @PostMapping("/user/logout")
    public ResponseEntity<String> logout() {
        // Logic to invalidate user session (requires authentication)
        boolean success = userService.logout();
        if (success) {
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logout failed");
        }
    }

    @PostMapping("/public/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestParam String email, @RequestParam String verificationCode) {
        boolean success = userService.verifyEmail(email, verificationCode);
        if (success) {
            return ResponseEntity.ok("Email verification successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email verification failed");
        }
    }

    @PostMapping("/public/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestParam String email) {
        boolean success = userService.sendPasswordResetEmail(email);
        if (success) {
            return ResponseEntity.ok("Password reset email sent successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send password reset email");
        }
    }

    @PostMapping("/public/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String email, @RequestParam String resetCode, @RequestParam String newPassword) {
        boolean success = userService.resetPassword(email, resetCode, newPassword);
        if (success) {
            return ResponseEntity.ok("Password reset successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Password reset failed");
        }
    }
}
