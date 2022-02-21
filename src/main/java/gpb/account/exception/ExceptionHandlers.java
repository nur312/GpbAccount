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

    @ExceptionHandler({AccountFrozenException.class})
    public ResponseEntity<ResponseDetails> handleAccountFrozenException (AccountFrozenException ex){
        ResponseDetails responseDetails = new ResponseDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        responseDetails.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDetails, headers, HttpStatus.LOCKED); //TODO LOCKED?
    }
    @ExceptionHandler({AccountNotFoundException.class})
    public ResponseEntity<ResponseDetails> handleAccountNotFoundException (AccountNotFoundException ex){
        ResponseDetails responseDetails = new ResponseDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        responseDetails.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDetails, headers, HttpStatus.NOT_FOUND); //TODO LOCKED?
    }
    @ExceptionHandler({NotSufficientFundsException.class})
    public ResponseEntity<ResponseDetails> handleNotSufficientFundsException (NotSufficientFundsException ex){
        ResponseDetails responseDetails = new ResponseDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        responseDetails.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDetails, headers, HttpStatus.NOT_ACCEPTABLE); //TODO ?
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ResponseDetails> handleIllegalArgumentException (IllegalArgumentException ex){
        ResponseDetails responseDetails = new ResponseDetails();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        responseDetails.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDetails, headers, HttpStatus.BAD_REQUEST);
        //TODO Оставить этот эксепшн или написать свой?
    }
}
