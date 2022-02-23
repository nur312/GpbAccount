package gpb.account.exception;

public class AccountFrozenException extends CustomException {

    public AccountFrozenException(String message, Integer accountNum){
        super(message, accountNum);
    }

}
