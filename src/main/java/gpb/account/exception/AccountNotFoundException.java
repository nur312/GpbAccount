package gpb.account.exception;

public class AccountNotFoundException extends CustomException {

    public AccountNotFoundException(String message, Integer accountNum){
        super(message, accountNum);
    }


}
