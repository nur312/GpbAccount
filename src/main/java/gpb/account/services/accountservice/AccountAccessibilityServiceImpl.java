package gpb.account.services.accountservice;

import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;
import org.springframework.stereotype.Service;

@Service
public class AccountAccessibilityServiceImpl implements AccountAccessibilityService {

    private final AccountRepo accountRepo;

    public AccountAccessibilityServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void freezeAccount(Integer account_no) {

        ExceptionStateChecker.throwExIdDoesNotExist(account_no, accountRepo);

        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(true);

        accountRepo.save(account);

    }

    public void unfreezeAccount(Integer account_no) {

        ExceptionStateChecker.throwExIdDoesNotExist(account_no, accountRepo);
        AccountEntity account = accountRepo.getById(account_no);

        account.setFrozen(false);

        accountRepo.save(account);

    }
}
