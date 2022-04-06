package gpb.account.services;

import gpb.account.dto.Account;
import gpb.account.dto.Operation;
import gpb.account.entity.AccountEntity;
import gpb.account.exception.InvalidJsonException;
import gpb.account.exception.NotSufficientFundsException;
import gpb.account.repo.AccountRepo;
import gpb.account.services.accountservice.ExceptionStateChecker;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    
    private final AccountRepo accountRepo;
    
    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }
    
    @Override
    public void freezeAccount(Integer account_no) {
        ExceptionStateChecker.throwExIdDoesNotExist(account_no, accountRepo);

        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(true);

        accountRepo.save(account);
    }

    @Override
    public void unfreezeAccount(Integer account_no) {
        ExceptionStateChecker.throwExIdDoesNotExist(account_no, accountRepo);
        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(false);

        accountRepo.save(account);
    }
    @Override
    public Account getAccount(Integer accountNo) {

        ExceptionStateChecker.throwExIdDoesNotExist(accountNo, accountRepo);

        AccountEntity accountEntity = accountRepo.getById(accountNo);

        return new Account(accountEntity.getAccountNo(),
                accountEntity.getClientId(), accountEntity.getActualBalance(), accountEntity.getFrozen(),
                accountEntity.getClientType(), accountEntity.getAccountType());
    }
    @Override
    public Integer createAccount(Account account) {
        if (account.getClientId() == null || account.getClientType() == null || account.getAccountType() == null) {
            throw new InvalidJsonException("Invalid JSON data", account.getAccountNumber());
        }
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setClientType(account.getClientType());
        accountEntity.setClientId(account.getClientId());
        accountEntity.setAccountType(account.getAccountType());
        accountEntity.setFrozen(false);
        accountEntity.setActualBalance(0.0);

        accountEntity = accountRepo.save(accountEntity);


        return accountEntity.getAccountNo();
    }
    @Override
    public void deposit(Operation operation) {

        if (operation.getAccountNo() == null || operation.getAmount() <= 0) {
            throw new InvalidJsonException("Invalid JSON data for deposit", operation.getAccountNo());
        }

        ExceptionStateChecker.throwExIdDoesNotExist(operation.getAccountNo(), accountRepo);

        // SELECT * FROM account WHERE account_no = {account_no}
        AccountEntity account = accountRepo.getById(operation.getAccountNo());

        ExceptionStateChecker.throwExIfAccountFrozen(account, accountRepo);

        double newActualBalance = account.getActualBalance() + operation.getAmount();

        account.setActualBalance(newActualBalance);

        accountRepo.save(account);
    }
    @Override
    public void withdraw(Operation operation) {
        if (operation.getAccountNo() == null || operation.getAmount() <= 0) {
            throw new InvalidJsonException("Invalid JSON data for withdraw", operation.getAccountNo());
        }

        ExceptionStateChecker.throwExIdDoesNotExist(operation.getAccountNo(), accountRepo);

        AccountEntity account = accountRepo.getById(operation.getAccountNo());


        ExceptionStateChecker.throwExIfAccountFrozen(account, accountRepo);

        double newActualBalance = account.getActualBalance() - operation.getAmount();
        if (newActualBalance < 0) {
            throw new NotSufficientFundsException("Balance cannot be negative", operation.getAccountNo());
        }


        account.setActualBalance(newActualBalance);
        accountRepo.save(account);
    }
}
