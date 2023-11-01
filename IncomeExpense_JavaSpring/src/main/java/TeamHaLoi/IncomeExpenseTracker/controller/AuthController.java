package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import org.springframework.beans.factory.annotation.Value;
import TeamHaLoi.IncomeExpenseTracker.payload.LoginDto;
import TeamHaLoi.IncomeExpenseTracker.payload.RegisterDto;
import TeamHaLoi.IncomeExpenseTracker.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;


    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Login API
    @PostMapping("/user_login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        UserAccount user = authService.authenticateUser(loginDto);

        if(user != null) {
            System.out.println("Authentication Successful");

            // Creating a map to store response data
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User authenticated successfully");
            response.put("userId", user.getId());

            // Returning map as response
            return ResponseEntity.ok(response);
        } else {
            System.out.println("Authentication Failed");
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

    // Register API
    @PostMapping(value = {"/user_register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        UserAccount user = authService.register(registerDto);
        if(user != null) {
            System.out.println("User creation Successful");
            return ResponseEntity.ok("User created successfully");
        } else {
            System.out.println("User creation Failed");
            return ResponseEntity.status(401).body("User creation failed");
        }
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint hit");
        return "Test successful!";
    }
}