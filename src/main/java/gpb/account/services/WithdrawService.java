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

    public void withdraw(int account_no, double amount) {
        boolean exists = accountRepo.existsById(account_no);
        if(!exists){
            throw new IllegalArgumentException("No such account with account_no = " + account_no);
        }
        AccountEntity account = accountRepo.getById(account_no);

        double newActualBalance = account.getActualBalance() - amount;
        if(newActualBalance < 0){
            throw new IllegalStateException("Balance cannot be negative for " + account_no);
        }
        account.setActualBalance(newActualBalance);
        accountRepo.save(account);

    }
}
