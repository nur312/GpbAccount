package gpb.account.services.accountservice;

import gpb.account.dto.Operation;
import gpb.account.entity.AccountEntity;
import gpb.account.exception.InvalidJsonException;
import gpb.account.exception.NotSufficientFundsException;
import gpb.account.repo.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountTransferServiceImpl implements AccountTransferService{

    private final AccountRepo accountRepo;

    public AccountTransferServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void deposit(Operation operation) {

        if (operation.getAccountNo() == null || operation.getAmount() <= 0) {
            throw new InvalidJsonException("Invalid JSON data for deposit", operation.getAccountNo());
        }

        Helper.throwExIdDoesNotExist(operation.getAccountNo(), accountRepo);

        // SELECT * FROM account WHERE account_no = {account_no}
        AccountEntity account = accountRepo.getById(operation.getAccountNo());

        Helper.throwExIfAccountFrozen(account, accountRepo);

        double newActualBalance = account.getActualBalance() + operation.getAmount();

        account.setActualBalance(newActualBalance);

        accountRepo.save(account);
    }

    public void withdraw(Operation operation) {
        if (operation.getAccountNo() == null || operation.getAmount() <= 0) {
            throw new InvalidJsonException("Invalid JSON data for withdraw", operation.getAccountNo());
        }

        Helper.throwExIdDoesNotExist(operation.getAccountNo(), accountRepo);

        AccountEntity account = accountRepo.getById(operation.getAccountNo());


        Helper.throwExIfAccountFrozen(account, accountRepo);

        double newActualBalance = account.getActualBalance() - operation.getAmount();
        if (newActualBalance < 0) {
            throw new NotSufficientFundsException("Balance cannot be negative", operation.getAccountNo());
        }


        account.setActualBalance(newActualBalance);
        accountRepo.save(account);
    }
}
