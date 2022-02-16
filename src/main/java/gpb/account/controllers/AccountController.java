package gpb.account.controllers;


import gpb.account.dto.Account;
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

    @GetMapping
    public String temp() {

        return "Hello GbpAccount!";
    }


}
