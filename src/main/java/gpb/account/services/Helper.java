package gpb.account.services;

import gpb.account.entity.AccountEntity;
import gpb.account.repo.AccountRepo;

public class Helper {

    public static void throwExIdDoesNotExist(Integer account_no, final AccountRepo accountRepo) {

        if (account_no == null) {
            throw new IllegalArgumentException("No such account with account_no = " + account_no);
        }

        boolean exists = accountRepo.existsById(account_no);
        if (!exists) {
            throw new IllegalArgumentException("No such account with account_no = " + account_no);
        }
    }

    public static void throwExIfAccountFrozen(AccountEntity account, final AccountRepo accountRepo) {

        if (account.getFrozen()) {

            throw new IllegalStateException("Account is frozen " + account.getAccountNo());
        }
    }
}
