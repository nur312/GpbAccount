package gpb.account.services.accountservice;

import gpb.account.dto.Operation;
import gpb.account.entity.AccountEntity;
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
            throw new IllegalArgumentException();
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
            throw new IllegalArgumentException();
        }

        Helper.throwExIdDoesNotExist(operation.getAccountNo(), accountRepo);

        AccountEntity account = accountRepo.getById(operation.getAccountNo());


        Helper.throwExIfAccountFrozen(account, accountRepo);

        double newActualBalance = account.getActualBalance() - operation.getAmount();
        if (newActualBalance < 0) {
            throw new IllegalStateException("Balance cannot be negative for " + operation.getAccountNo());
        }


        account.setActualBalance(newActualBalance);
        accountRepo.save(account);
    }
}
