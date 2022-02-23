package gpb.account.services.accountservice;

import gpb.account.dto.Account;

public interface AccountCrudService {

    Account getAccount(Integer accountNo);

    Integer createAccount(Account account);
}
