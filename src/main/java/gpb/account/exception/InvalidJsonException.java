package gpb.account.exception;

public class InvalidJsonException extends CustomException{
    public InvalidJsonException(String message, Integer accountNum){
        super(message, accountNum);
    }

}
