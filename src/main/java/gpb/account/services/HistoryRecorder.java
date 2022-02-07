package gpb.account.services;

import gpb.account.dto.CurrentAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;

@Service
public class HistoryRecorder {
    @Autowired
    private AccountRepo accountRepo;

    public void getAccountInfo(CurrentAccount account) {
        AccountEntity byClientId =
                accountRepo.findByClientId(account.getClientId());

    }
}
