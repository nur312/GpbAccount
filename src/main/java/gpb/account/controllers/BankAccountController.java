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
    @io.swagger.v3.oas.annotations.Operation(summary = "Запрос баланса банковского счета")
    public Double getActualBalance() {

        return bankAccountService.getActualBalance();
    }


    @PostMapping("/deposit")
    @io.swagger.v3.oas.annotations.Operation(summary = "Зачисление средств на счет банка")
    public ResponseDetails depositFunds(@RequestBody Integer amount) {

        bankAccountService.deposit(amount);

        return new ResponseDetails(null, "funds were deposited from bank's account", true);
    }

    @PostMapping("/withdraw")
    @io.swagger.v3.oas.annotations.Operation(summary = "Списание средств со счета банка")
    public ResponseDetails withdrawFunds(@RequestBody Integer amount) {

        bankAccountService.withdraw(amount);

        return new ResponseDetails(null, "funds were withdrawn from bank's account", true);
    }
}
