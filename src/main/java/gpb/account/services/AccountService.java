package gpb.account.services;

import gpb.account.dto.Account;
import gpb.account.dto.Operation;

public interface AccountService {

    void freezeAccount(Integer account_no);

    void unfreezeAccount(Integer account_no);

    Account getAccount(Integer accountNo);

    Integer createAccount(Account account);

    void deposit(Operation operation);

    void withdraw(Operation operation);

}
