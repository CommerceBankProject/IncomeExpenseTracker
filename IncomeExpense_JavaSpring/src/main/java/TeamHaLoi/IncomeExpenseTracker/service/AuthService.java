package TeamHaLoi.IncomeExpenseTracker.service;

import TeamHaLoi.IncomeExpenseTracker.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import TeamHaLoi.IncomeExpenseTracker.payload.LoginDto;
import TeamHaLoi.IncomeExpenseTracker.payload.RegisterDto;
import TeamHaLoi.IncomeExpenseTracker.model.UserAccount;
import TeamHaLoi.IncomeExpenseTracker.security.PasswordUtil;
import java.time.LocalDateTime;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public boolean authenticateUser(LoginDto loginDto) {
        // Log request data
        System.out.println("Authenticating User: " + loginDto.getEmail());
        UserAccount userAccount = userAccountRepository.findByEmail(loginDto.getEmail());
        // Check and log the authentication result
        boolean isAuthenticated = userAccount != null;
        String hashedPassword = PasswordUtil.hashPassword(loginDto.getPassword(), userAccount.getSalt());
        System.out.println("Is Authenticated: " + isAuthenticated);

        if (userAccount != null) {
            return hashedPassword.equals(userAccount.getPassword());
        } else {
            return false;
        }
    }



    public UserAccount register(RegisterDto registerDto) {
        // Create a new user account
        UserAccount newUser = new UserAccount();
        newUser.setEmail(registerDto.getEmail());
        newUser.setFirstName(registerDto.getFirstName());
        newUser.setLastName(registerDto.getLastName());
        // Generate Salt & Hash Password
        String salt = PasswordUtil.generateSalt();
        newUser.setSalt(salt);
        String hashedPassword = PasswordUtil.hashPassword(registerDto.getPassword(), salt);
        newUser.setPassword(hashedPassword);
        newUser.setCreatedAt(LocalDateTime.now());

        // Save the new user account
        userAccountRepository.save(newUser);

        return newUser;
    }

}