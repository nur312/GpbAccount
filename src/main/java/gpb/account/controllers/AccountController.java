package gpb.account.controllers;


import gpb.account.dto.Account;
import gpb.account.dto.Operation;
import gpb.account.dto.ResponseDetails;
import gpb.account.services.accountservice.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {


    private final AccountAccessibilityService accountAccessibilityService;

    private final AccountCrudService accountCrudService;

    private final AccountTransferService accountTransferService;

    public AccountController(AccountAccessibilityServiceImpl accountAccessibilityService, AccountCrudServiceImpl accountCrudService, AccountTransferServiceImpl accountTransferService) {
        this.accountAccessibilityService = accountAccessibilityService;
        this.accountCrudService = accountCrudService;
        this.accountTransferService = accountTransferService;
    }


    @PostMapping
    public ResponseDetails createAccount(@RequestBody Account account) {

        Integer accountNo = accountCrudService.createAccount(account);

        return new ResponseDetails(accountNo, "account had been created", true);
    }

    @GetMapping("/{accountNo}")
    public Account getAccountInfo(@PathVariable Integer accountNo) {


        return accountCrudService.getAccount(accountNo);
    }


    @PostMapping("/deposit")
    public ResponseDetails depositFunds(@RequestBody Operation operation) {

        accountTransferService.deposit(operation);

        return new ResponseDetails(operation.getAccountNo(), "funds were deposited", true);
    }

    @PostMapping("/withdraw")
    public ResponseDetails withdrawFunds(@RequestBody Operation operation) {

        accountTransferService.withdraw(operation);

        return new ResponseDetails(operation.getAccountNo(), "funds were withdrawn", true);
    }

    @PostMapping("/freeze/{accountNo}")
    public ResponseDetails freezeAccount(@PathVariable Integer accountNo) {


        accountAccessibilityService.freezeAccount(accountNo);

        return new ResponseDetails(accountNo, "Account was frozen", true);
    }

    @PostMapping("/unfreeze/{accountNo}")
    public ResponseDetails unfreezeAccount(@PathVariable Integer accountNo) {

        accountAccessibilityService.unfreezeAccount(accountNo);

        return new ResponseDetails(accountNo, "Account was unfrozen", true);
    }


}
