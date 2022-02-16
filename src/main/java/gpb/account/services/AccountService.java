package gpb.account.services;

import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepo accountRepo;

    private void throwExIdDoesNotExist(int account_no) {
        boolean exists = accountRepo.existsById(account_no);
        if (!exists) {
            throw new IllegalArgumentException("No such account with account_no = " + account_no);
        }
    }

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void deposit(int account_no, double amount) {

        throwExIdDoesNotExist(account_no);

        // SELECT * FROM account WHERE account_no = {account_no}
        AccountEntity account = accountRepo.getById(account_no);

        double newActualBalance = account.getActualBalance() + amount;

        account.setActualBalance(newActualBalance);

        accountRepo.save(account);
    }

    public void withdraw(int account_no, double amount) {

        throwExIdDoesNotExist(account_no);

        AccountEntity account = accountRepo.getById(account_no);

        double newActualBalance = account.getActualBalance() - amount;
        if (newActualBalance < 0) {
            throw new IllegalStateException("Balance cannot be negative for " + account_no);
        }
        account.setActualBalance(newActualBalance);
        accountRepo.save(account);
    }

    public double getActualBalance(int account_no) {

        throwExIdDoesNotExist(account_no);

        AccountEntity account = accountRepo.getById(account_no);

        return account.getActualBalance();
    }

    public void createAccount(AccountEntity account) {


        // ToDo: при создании баланса не должно быть итд

        accountRepo.save(account);
    }
}
