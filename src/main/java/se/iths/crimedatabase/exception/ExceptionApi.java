package se.iths.crimedatabase.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionApi {

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-mm-yyyy hh:mm:ss" )
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String details;

    public ExceptionApi(){
        timestamp = LocalDateTime.now();
    }

    public ExceptionApi(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
        this.details = ex.getLocalizedMessage();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
