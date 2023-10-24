package TeamHaLoi.IncomeExpenseTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserBankAccountLinkNotFoundException extends RuntimeException {
    public UserBankAccountLinkNotFoundException(String id) {
        super("Could not find UserBankAccountLink with id: " + id);
    }
}
