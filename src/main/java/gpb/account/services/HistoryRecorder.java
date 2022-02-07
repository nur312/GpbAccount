package gpb.account.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import gpb.account.dto.AbstractAccount;
import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;

@Service
public class HistoryRecorder {
    @Autowired
    private AccountRepo accountRepo;

    public void getAccountInfo(AbstractAccount abstractAccount) {
        AccountEntity byClientId =
                accountRepo.findByClientId(abstractAccount.getClientId());

    }
}
