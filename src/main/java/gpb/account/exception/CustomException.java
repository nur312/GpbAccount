package gpb.account.exception;

abstract class CustomException extends RuntimeException{
    private Integer accountNo;
    public Integer getAccountNo(){
        return accountNo;
    }

    public CustomException(String message, Integer accountNum){
        super(message);
        accountNo = accountNum;
    }

}
