package gpb.account.exception;

import gpb.account.dto.ResponseDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ExceptionHandlers {

    private ResponseEntity<ResponseDetails> createResponse(String message, Integer accountNo, HttpStatus status){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseDetails responseDetails = new ResponseDetails();
        responseDetails.setMessage(message);
        responseDetails.setAccountNo(accountNo);
        responseDetails.setIsOk(false);
        return new ResponseEntity<>(responseDetails, headers, status);
    }

    @ExceptionHandler({AccountFrozenException.class})
    public ResponseEntity<ResponseDetails> handleAccountFrozenException (AccountFrozenException ex){

        return createResponse(ex.getMessage(), ex.getAccountNo(), HttpStatus.LOCKED);
    }

    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<ResponseDetails> handleAccountNotFoundException (AccountNotFoundException ex){

        return createResponse(ex.getMessage(), ex.getAccountNo(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({NotSufficientFundsException.class})
    public ResponseEntity<ResponseDetails> handleNotSufficientFundsException (NotSufficientFundsException ex){

       return createResponse(ex.getMessage(), ex.getAccountNo(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({InvalidJsonException.class})
    public ResponseEntity<ResponseDetails> handleInvalidJsonException (InvalidJsonException ex){

        return createResponse(ex.getMessage(), ex.getAccountNo(), HttpStatus.BAD_REQUEST);
    }
}
