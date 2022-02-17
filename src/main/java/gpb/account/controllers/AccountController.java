package gpb.account.controllers;


import gpb.account.dto.Account;
import gpb.account.dto.Operation;
import gpb.account.entity.AccountEntity;
import gpb.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;


    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public ResponseEntity createAccount(@RequestBody Account account) {

        // ToDo: добавть в сервис логику создания элемента.

        accountService.createAccount(account);
        String answer = "account created";
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/deposit")
    public ResponseEntity depositFunds(@RequestBody Operation operation) {
        accountService.deposit(operation);
        String answer = "funds were deposited";
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/withdraw")
    public ResponseEntity withdrawFunds(@RequestBody Operation operation) {
        accountService.withdraw(operation);
        String answer = "funds were withdrawn";
        return ResponseEntity.ok(answer);
    }

    @GetMapping("/{account_no}")
    public Operation getActualBalance(@PathVariable Integer account_no) {

        return new Operation(account_no, accountService.getActualBalance(account_no));
    }


}
