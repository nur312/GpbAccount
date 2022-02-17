package gpb.account.services;

import gpb.account.dto.Account;
import gpb.account.dto.Operation;
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

    public void deposit(Operation operation) {

        if(operation.getAccountNo() == null || operation.getAmount() <= 0){
            throw new IllegalArgumentException();
        }

        throwExIdDoesNotExist(operation.getAccountNo());

        // SELECT * FROM account WHERE account_no = {account_no}
        AccountEntity account = accountRepo.getById(operation.getAccountNo());

        double newActualBalance = account.getActualBalance() + operation.getAmount();

        account.setActualBalance(newActualBalance);

        accountRepo.save(account);
    }

    public void withdraw(Operation operation) {
        if(operation.getAccountNo() == null || operation.getAmount() <= 0){
            throw new IllegalArgumentException();
        }

        throwExIdDoesNotExist(operation.getAccountNo());

        AccountEntity account = accountRepo.getById(operation.getAccountNo());

        double newActualBalance = account.getActualBalance() - operation.getAmount();
        if (newActualBalance < 0) {
            throw new IllegalStateException("Balance cannot be negative for " + operation.getAccountNo());
        }
        account.setActualBalance(newActualBalance);
        accountRepo.save(account);
    }

    public double getActualBalance(int account_no) {

        throwExIdDoesNotExist(account_no);

        AccountEntity account = accountRepo.getById(account_no);

        return account.getActualBalance();
    }

    public void createAccount(Account account) {
        if(account.getClientId() == null || account.getClientType() == null || account.getAccountType() == null){
            throw new IllegalArgumentException();
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setClientType(account.getClientType());
        accountEntity.setClientId(account.getClientId());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setFrozen(false);
        accountEntity.setActualBalance(0.0);
        // ToDo: при создании баланса не должно быть итд

        accountRepo.save(accountEntity);
    }
}
