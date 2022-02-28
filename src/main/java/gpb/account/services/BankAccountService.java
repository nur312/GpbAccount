package gpb.account.services;


import gpb.account.entity.BankAccountEntity;
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

        if(!bankAccountRepo.existsById(bankAccountNo)) {

            var bankAccount = new BankAccountEntity();

            bankAccount.setBankAccountNo(bankAccountNo);
            bankAccount.setFunds(0.0);

            bankAccountRepo.save(bankAccount);
        }
    }

    public void deposit(double amount) {

        BankAccountEntity bankAccount = bankAccountRepo.getById(bankAccountNo);
        double newDepositedFunds = bankAccount.getFunds() + amount;

        bankAccount.setFunds(newDepositedFunds);
        bankAccountRepo.save(bankAccount);
    }

    public void withdraw(double amount) {

        BankAccountEntity bankAccount = bankAccountRepo.getById(bankAccountNo);

        double newCreditedFunds = bankAccount.getFunds() - amount;
        if (newCreditedFunds < 0) {

            throw new NotSufficientFundsException("No enough money to withdraw from bank", null);
        }
        bankAccount.setFunds(newCreditedFunds);
        bankAccountRepo.save(bankAccount);
    }

    public  double getActualBalance() {

        BankAccountEntity bankAccount = bankAccountRepo.getById(bankAccountNo);

        return bankAccount.getFunds();
    }

}
