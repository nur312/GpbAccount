package gpb.account.exception;

public class NotSufficientFundsException extends RuntimeException {
    public NotSufficientFundsException(String message){
        super(message);
    }
}
