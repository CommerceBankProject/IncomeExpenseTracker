package TeamHaLoi.IncomeExpenseTracker.controller;

<<<<<<< HEAD

import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
=======
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import org.springframework.beans.factory.annotation.Value;
>>>>>>> bc90c6bc9233fc5248b64a20ae8062b6dbb4e5da
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

<<<<<<< HEAD
    // Build Login REST API
    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/user_login")//The URL backend: localhost:8080/auth/user_register
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        //Define the user variable
       boolean auth = authService.authenticateUser(loginDto);
        if(auth) {
           System.out.println("Authentication Successful");
           return ResponseEntity.ok("User authenticated successfully");
       } else {
            System.out.println("Authentication Failed");
           return ResponseEntity.status(401).body("Authentication failed");
        }
=======
    // Login API
    @PostMapping("/user_login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginDto loginDto) {
        // Define the user variable
        boolean auth = authService.authenticateUser(loginDto);
>>>>>>> bc90c6bc9233fc5248b64a20ae8062b6dbb4e5da

        if(auth) {
            System.out.println("Authentication Successful");
            return ResponseEntity.ok("User authenticated successfully");
        } else {
            System.out.println("Authentication Failed");
            return ResponseEntity.status(401).body("Authentication failed");
        }
    }

<<<<<<< HEAD
    //Build REST API for the backend to connect with the Frontend
    @CrossOrigin(origins = "http://localhost:5173") //Allow the access from the front end port here is 5173
    @PostMapping("/user_register")// The URL backend: localhost:8080/auth/user_register
    public ResponseEntity<String> registerResponse(@RequestBody RegisterDto registerDto)
    {
=======
    // Register API
    @PostMapping(value = {"/user_register", "/signup"})
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
>>>>>>> bc90c6bc9233fc5248b64a20ae8062b6dbb4e5da
        UserAccount user = authService.register(registerDto);
        if(user != null) {
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.status(401).body("User creation failed");
        }
<<<<<<< HEAD
        //System.out.println("Test endpoint hit");
=======
>>>>>>> bc90c6bc9233fc5248b64a20ae8062b6dbb4e5da
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint hit");
        return "Test successful!";
    }
}