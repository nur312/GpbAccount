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

    private void throwExIdDoesNotExist(Integer account_no) {

        if (account_no == null) {
            throw new IllegalArgumentException("No such account with account_no = " + account_no);
        }

        boolean exists = accountRepo.existsById(account_no);
        if (!exists) {
            throw new IllegalArgumentException("No such account with account_no = " + account_no);
        }
    }

    private void throwExIfAccountFrozen(AccountEntity account) {

        if (account.getFrozen()) {

            throw new IllegalStateException("Account is frozen " + account.getAccountNo());
        }
    }

    @Autowired
    public AccountService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccount(Integer accountNo) {

        throwExIdDoesNotExist(accountNo);

        AccountEntity accountEntity = accountRepo.getById(accountNo);

        return new Account(accountEntity.getAccountNo(),
                accountEntity.getClientId(), accountEntity.getActualBalance(), accountEntity.getFrozen(),
                accountEntity.getClientType(), accountEntity.getAccountType());
    }

    public void deposit(Operation operation) {

        if (operation.getAccountNo() == null || operation.getAmount() <= 0) {
            throw new IllegalArgumentException();
        }

        throwExIdDoesNotExist(operation.getAccountNo());

        // SELECT * FROM account WHERE account_no = {account_no}
        AccountEntity account = accountRepo.getById(operation.getAccountNo());

        throwExIfAccountFrozen(account);

        double newActualBalance = account.getActualBalance() + operation.getAmount();

        account.setActualBalance(newActualBalance);

        accountRepo.save(account);
    }

    public void withdraw(Operation operation) {
        if (operation.getAccountNo() == null || operation.getAmount() <= 0) {
            throw new IllegalArgumentException();
        }

        throwExIdDoesNotExist(operation.getAccountNo());

        AccountEntity account = accountRepo.getById(operation.getAccountNo());


        throwExIfAccountFrozen(account);

        double newActualBalance = account.getActualBalance() - operation.getAmount();
        if (newActualBalance < 0) {
            throw new IllegalStateException("Balance cannot be negative for " + operation.getAccountNo());
        }


        account.setActualBalance(newActualBalance);
        accountRepo.save(account);
    }

    public double getActualBalance(Integer account_no) {

        throwExIdDoesNotExist(account_no);

        AccountEntity account = accountRepo.getById(account_no);

        return account.getActualBalance();
    }

    public Integer createAccount(Account account) {
        if (account.getClientId() == null || account.getClientType() == null || account.getAccountType() == null) {
            throw new IllegalArgumentException();
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setClientType(account.getClientType());
        accountEntity.setClientId(account.getClientId());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setFrozen(false);
        accountEntity.setActualBalance(0.0);
        // ToDo: при создании баланса не должно быть итд

        accountEntity = accountRepo.save(accountEntity);


        return accountEntity.getAccountNo();
    }

    public void freezeAccount(Integer account_no) {
        // ToDo: можно ли в контроллере передвавть boolean, чтобы сразу здесь установить?
        // Пока что мы считаем, что это логика хоть и простая, поэтому разделили
        throwExIdDoesNotExist(account_no);

        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(true);

        accountRepo.save(account);

    }

    public void unfreezeAccount(Integer account_no) {

        throwExIdDoesNotExist(account_no);
        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(false);

        accountRepo.save(account);

    }
}
