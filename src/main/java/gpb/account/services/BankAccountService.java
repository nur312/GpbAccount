package gpb.account.services;


import gpb.account.entity.BankAccountEntity;
import gpb.account.exception.InvalidJsonException;
import gpb.account.exception.NotSufficientFundsException;
import gpb.account.repo.BankAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BankAccountService {
    private final BankAccountRepo bankAccountRepo;
    private final Integer bankAccountNo;

    @Autowired
    public BankAccountService(BankAccountRepo bankAccountRepo, @Value("${bank.account}") Integer bankAccountNo) {

        this.bankAccountRepo = bankAccountRepo;
        this.bankAccountNo = bankAccountNo;

        if (!bankAccountRepo.existsById(this.bankAccountNo)) {

            var bankAccount = new BankAccountEntity();

            bankAccount.setBankAccountNo(this.bankAccountNo);
            bankAccount.setFunds(0.0);

            bankAccountRepo.save(bankAccount);
        }
    }

    public void deposit(double amount) {

        BankAccountEntity bankAccount = bankAccountRepo.getById(bankAccountNo);
        if (amount <= 0.0) {
            throw new InvalidJsonException("Amount cannot be equal  or less than  zero", 0);
        }
        double newDepositedFunds = bankAccount.getFunds() + amount;

        bankAccount.setFunds(newDepositedFunds);
        bankAccountRepo.save(bankAccount);
    }

    public void withdraw(double amount) {

        BankAccountEntity bankAccount = bankAccountRepo.getById(bankAccountNo);
        if (amount <= 0.0) {
            throw new InvalidJsonException("Amount cannot be equal  or less than  zero", 0);
        }
        double newCreditedFunds = bankAccount.getFunds() - amount;
        if (newCreditedFunds < 0) {

            throw new NotSufficientFundsException("No enough money to withdraw from bank", null);
        }
        bankAccount.setFunds(newCreditedFunds);
        bankAccountRepo.save(bankAccount);
    }

    public double getActualBalance() {

        BankAccountEntity bankAccount = bankAccountRepo.getById(bankAccountNo);

        return bankAccount.getFunds();
    }

}
