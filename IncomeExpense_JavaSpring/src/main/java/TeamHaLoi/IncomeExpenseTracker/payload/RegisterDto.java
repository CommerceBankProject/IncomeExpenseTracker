package TeamHaLoi.IncomeExpenseTracker.payload;
import java.time.LocalDateTime;

public class RegisterDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String salt;
    private String rePassword;
    private LocalDateTime createdAt;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setRePassword(String rePassword)
    {
        this.rePassword = rePassword;
    }

    public String getRePassword()
    {
        return rePassword;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }



}