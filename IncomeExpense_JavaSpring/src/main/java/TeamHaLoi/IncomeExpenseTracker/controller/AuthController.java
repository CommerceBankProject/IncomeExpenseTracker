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
        // Define the user variable
        boolean auth = authService.authenticateUser(loginDto);

        if(auth) {
            System.out.println("Authentication Successful");
            return ResponseEntity.ok("User authenticated successfully");
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
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.status(401).body("User creation failed");
        }
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint hit");
        return "Test successful!";
    }
}