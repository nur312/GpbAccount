package gpb.account.exception;

public class AccountNotFoundException extends RuntimeException {

    private Integer accountNo;
    public Integer getAccountNo(Integer accountNo){
        return accountNo;
    }

    public AccountNotFoundException(String message, Integer accountNum){
        super(message);
        accountNo = accountNum;
    }

}
