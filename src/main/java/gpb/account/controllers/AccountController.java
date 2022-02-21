package gpb.account.controllers;


import gpb.account.dto.Account;
import gpb.account.dto.Operation;
import gpb.account.dto.ResponseDetails;
import gpb.account.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseDetails createAccount(@RequestBody Account account) {

        Integer accountNo = accountService.createAccount(account);


        return new ResponseDetails(accountNo, "account had been created", true);
    }

    @GetMapping("/{accountNo}")
    public Account getAccountInfo(@PathVariable Integer accountNo) {


        return accountService.getAccount(accountNo);
    }


    @PostMapping("/deposit")
    public ResponseDetails depositFunds(@RequestBody Operation operation) {
        accountService.deposit(operation);

        return new ResponseDetails(operation.getAccountNo(), "funds were deposited", true);
    }

    @PostMapping("/withdraw")
    public ResponseDetails withdrawFunds(@RequestBody Operation operation) {

        // ToDo: подумать, стоит ли релизовать метод для перевода межу двумя счетами?
        // Пока что не сделали, потому что есть другой сервис, который этим занимается

        accountService.withdraw(operation);

        return new ResponseDetails(operation.getAccountNo(), "funds were withdrawn", true);
    }

    @PostMapping("/freeze/{accountNo}")
    public ResponseDetails freezeAccount(@PathVariable Integer accountNo) {


        accountService.freezeAccount(accountNo);

        return new ResponseDetails(accountNo, "Account was frozen", true);
    }

    @PostMapping("/unfreeze/{accountNo}")
    public ResponseDetails unfreezeAccount(@PathVariable Integer accountNo) {

        accountService.unfreezeAccount(accountNo);

        return new ResponseDetails(accountNo, "Account was unfrozen", true);
    }


}
