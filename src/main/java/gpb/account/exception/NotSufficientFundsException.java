package gpb.account.exception;

public class NotSufficientFundsException extends RuntimeException {

    private Integer accountNo;
    public Integer getAccountNo(Integer accountNo){
        return accountNo;
    }

    public NotSufficientFundsException(String message, Integer accountNum){
        super(message);
        accountNo = accountNum;
    }

//    ToDo обсудить, надо ли вставлять данные о том, насколько не хватает денег(newActualBalance)

}
