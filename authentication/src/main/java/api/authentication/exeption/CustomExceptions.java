package api.authentication.exeption;

import org.springframework.http.HttpStatus;

public class CustomExceptions extends RuntimeException{
    private HttpStatus status;

    public CustomExceptions(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
