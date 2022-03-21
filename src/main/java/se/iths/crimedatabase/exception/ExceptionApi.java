package se.iths.crimedatabase.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionApi {

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-mm-yyyy hh:mm:ss" )
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String statusDescription;
    private String message;

    public ExceptionApi(){
        timestamp = LocalDateTime.now();
    }

    public ExceptionApi(HttpStatus status, String statusDescription, Throwable ex) {
        this();
        this.status = status;
        this.statusDescription = statusDescription;
        this.message = ex.getLocalizedMessage();
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

    public String getStatusDescription() {
        return statusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        this.statusDescription = statusDescription;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
