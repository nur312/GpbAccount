package gpb.account.exception;

import gpb.account.dto.Account;
import gpb.account.dto.Operation;
import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public class ExceptionHandler {

    private void throwExIfAccountFrozen(AccountEntity account) {

        if (account.getFrozen()) {

            throw new IllegalStateException("Account is frozen " + account.getAccountNo());
        }
    }



}
