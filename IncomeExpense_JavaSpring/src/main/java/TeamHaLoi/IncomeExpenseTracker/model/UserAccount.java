package TeamHaLoi.IncomeExpenseTracker.model;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table((name="user_account"))
public class UserAccount {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String salt;
    @NotBlank
    private String full_name;
    @NotBlank
    private String date_created;
    @NotBlank
    private String last_updated;
    public UserAccount(){
        super();
    }
    public UserAccount(Long id, String email, String password, String full_name){
        super();
        this.id = id;
        this.email = email;
        this.password = password;
        this.full_name = full_name;
    }
    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setFull_name(String full_name){
        this.full_name = full_name;
    }
}