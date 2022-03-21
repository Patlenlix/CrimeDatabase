package se.iths.crimedatabase.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ExceptionApi error) {
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> handleBadRequest(BadRequestException exception) {
        String message = "Bad request to server";
        return buildResponseEntity(new ExceptionApi(HttpStatus.BAD_REQUEST, message, exception));
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleNotFound(NotFoundException exception) {
        String message = "No entity found";
        return buildResponseEntity(new ExceptionApi(HttpStatus.NOT_FOUND, message, exception));
    }

    @ExceptionHandler({MethodNotAllowedException.class})
    public ResponseEntity<?> handleNotAllowed(MethodNotAllowedException exception){
        String message = "Method not allowed";
        return buildResponseEntity(new ExceptionApi(HttpStatus.METHOD_NOT_ALLOWED,message,exception));
    }


}
