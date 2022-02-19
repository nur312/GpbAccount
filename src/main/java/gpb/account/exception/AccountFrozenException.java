package gpb.account.exception;

import gpb.account.entity.AccountEntity;

public class AccountFrozenException extends RuntimeException {

    private Integer accountNo;
    public Integer getAccountNo(Integer accountNo){
        return accountNo;
    }

    public AccountFrozenException(String message, Integer accountNum){
        super(message);
        accountNo = accountNum;
    }

}
