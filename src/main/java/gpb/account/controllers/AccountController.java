package gpb.account.controllers;


import gpb.account.dto.Account;
import gpb.account.dto.Operation;
import gpb.account.dto.ResponseDetails;
import gpb.account.services.AccountService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.context.annotation.Description;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    @io.swagger.v3.oas.annotations.Operation(summary = "Создание счета")
    public ResponseDetails createAccount(@RequestBody Account account) {

        Integer accountNo = accountService.createAccount(account);
        return new ResponseDetails(accountNo, "account had been created", true);
    }

    @GetMapping("/{accountNo}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Получить информацию о счете")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "запрос аккаунта выполнен"),
//            @ApiResponse(responseCode = "404", description = "Not Available", content = @Content(mediaType = "application/json"))})
    public Account getAccountInfo(@PathVariable Integer accountNo) {
        return accountService.getAccount(accountNo);
    }

    @PostMapping("/deposit")
    @io.swagger.v3.oas.annotations.Operation(summary = "Внесение средств на счет")

    public ResponseDetails depositFunds(@RequestBody Operation operation) {

        accountService.deposit(operation);
        return new ResponseDetails(operation.getAccountNo(), "funds were deposited", true);
    }

    @PostMapping("/withdraw")
    @io.swagger.v3.oas.annotations.Operation(summary = "Списание средств со счета")

    public ResponseDetails withdrawFunds(@RequestBody Operation operation) {

        accountService.withdraw(operation);
        return new ResponseDetails(operation.getAccountNo(), "funds were withdrawn", true);
    }

    @PostMapping("/freeze/{accountNo}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Заболокировать счет")

    public ResponseDetails freezeAccount(@PathVariable Integer accountNo) {

        accountService.freezeAccount(accountNo);
        return new ResponseDetails(accountNo, "Account was frozen", true);
    }

    @PostMapping("/unfreeze/{accountNo}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Разблокировать счет")

    public ResponseDetails unfreezeAccount(@PathVariable Integer accountNo) {

        accountService.unfreezeAccount(accountNo);
        return new ResponseDetails(accountNo, "Account was unfrozen", true);
    }
}
