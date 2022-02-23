package gpb.account.services.accountservice;

import gpb.account.dto.Account;
import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountCrudServiceImpl implements AccountCrudService{

    private final AccountRepo accountRepo;

    public AccountCrudServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public Account getAccount(Integer accountNo) {

        Helper.throwExIdDoesNotExist(accountNo, accountRepo);

        AccountEntity accountEntity = accountRepo.getById(accountNo);

        return new Account(accountEntity.getAccountNo(),
                accountEntity.getClientId(), accountEntity.getActualBalance(), accountEntity.getFrozen(),
                accountEntity.getClientType(), accountEntity.getAccountType());
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


}
