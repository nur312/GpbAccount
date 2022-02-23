package gpb.account.services.accountservice;

import gpb.account.dto.Operation;

public interface AccountTransferService {

    void deposit(Operation operation);

    void withdraw(Operation operation);
}
