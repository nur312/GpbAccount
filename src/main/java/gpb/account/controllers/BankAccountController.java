package gpb.account.controllers;

import gpb.account.dto.ResponseDetails;
import gpb.account.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bank")
public class BankAccountController {


    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {

        this.bankAccountService = bankAccountService;
    }


    @GetMapping("/balance")
    public Double getActualBalance() {

        return bankAccountService.getActualBalance();
    }


    @PostMapping("/deposit")
    public ResponseDetails depositFunds(@RequestBody Integer amount) {

        bankAccountService.deposit(amount);

        return new ResponseDetails(null, "funds were deposited from bank's account", true);
    }

    @PostMapping("/withdraw")
    public ResponseDetails withdrawFunds(@RequestBody Integer amount) {

        bankAccountService.withdraw(amount);

        return new ResponseDetails(null, "funds were withdrawn from bank's account", true);
    }
}
