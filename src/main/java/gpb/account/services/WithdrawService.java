package gpb.account.services;

import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WithdrawService {

    private final AccountRepo accountRepo;

    @Autowired
    public WithdrawService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public void withdraw(AccountEntity account) {

        // ToDo снять деньги
        accountRepo.save(account);
    }
}
