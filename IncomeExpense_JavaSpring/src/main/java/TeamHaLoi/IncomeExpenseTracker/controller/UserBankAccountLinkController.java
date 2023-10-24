package TeamHaLoi.IncomeExpenseTracker.controller;

import TeamHaLoi.IncomeExpenseTracker.model.UserBankAccountLink;
import TeamHaLoi.IncomeExpenseTracker.repository.UserBankAccountLinkRepository;
import TeamHaLoi.IncomeExpenseTracker.exception.UserBankAccountLinkNotFoundException; // You should have an appropriate exception or reuse from your existing ones.
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user_bank_account_links")
public class UserBankAccountLinkController {

    @Autowired
    private UserBankAccountLinkRepository userBankAccountLinkRepository;

    // Get All Links
    @GetMapping
    public List<UserBankAccountLink> getAllLinks() {
        return userBankAccountLinkRepository.findAll();
    }

    // Create a new Link
    @PostMapping
    public UserBankAccountLink createLink(@Valid @RequestBody UserBankAccountLink userBankAccountLink) {
        return userBankAccountLinkRepository.save(userBankAccountLink);
    }

    // Get a Single Link by ID
    @GetMapping("/{id}")
    public UserBankAccountLink getLinkById(@PathVariable(value = "id") String linkIdStr) {
        try {
            Integer linkId = Integer.parseInt(linkIdStr);
            return userBankAccountLinkRepository.findById(linkId)
                    .orElseThrow(() -> new UserBankAccountLinkNotFoundException("UserBankAccountLink not found with id: " + linkId));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid link ID format: " + linkIdStr);
        }
    }


    // Delete a Link
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLink(@PathVariable(value = "id") String linkIdStr) {
        try {
            Integer linkId = Integer.parseInt(linkIdStr);
            UserBankAccountLink link = userBankAccountLinkRepository.findById(linkId)
                    .orElseThrow(() -> new UserBankAccountLinkNotFoundException("UserBankAccountLink not found with id: " + linkIdStr));

            userBankAccountLinkRepository.delete(link);

            return ResponseEntity.ok().build();
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Invalid link ID format: " + linkIdStr);
        }
    }

}
