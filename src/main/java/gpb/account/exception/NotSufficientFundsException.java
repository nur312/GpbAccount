package gpb.account.exception;

public class NotSufficientFundsException extends CustomException {

    public NotSufficientFundsException(String message, Integer accountNum){
        super(message, accountNum);
    }
}
