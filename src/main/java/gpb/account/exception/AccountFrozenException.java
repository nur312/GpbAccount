package gpb.account.exception;

public class AccountFrozenException extends RuntimeException {
    public AccountFrozenException(String message){
        super(message);
    }
}
