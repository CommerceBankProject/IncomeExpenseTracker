package TeamHaLoi.IncomeExpenseTracker.controller;


import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.payload.LoginDto;
import TeamHaLoi.IncomeExpenseTracker.payload.RegisterDto;
import TeamHaLoi.IncomeExpenseTracker.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

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

    }

    //Build REST API for the backend to connect with the Frontend
    @CrossOrigin(origins = "http://localhost:5173") //Allow the access from the front end port here is 5173
    @PostMapping("/user_register")// The URL backend: localhost:8080/auth/user_register
    public ResponseEntity<String> registerResponse(@RequestBody RegisterDto registerDto)
    {
        UserAccount user = authService.register(registerDto);
        if(user != null) {
            return ResponseEntity.ok("User created successfully");
        } else {
            return ResponseEntity.status(401).body("User creation failed");
        }
        //System.out.println("Test endpoint hit");
    }

    @GetMapping("/test")
    public String test() {
        System.out.println("Test endpoint hit");
        return "Test successful!";
    }
}