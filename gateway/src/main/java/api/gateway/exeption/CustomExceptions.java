package api.gateway.exeption;

import org.springframework.http.HttpStatus;

public class CustomExceptions extends Exception{
    private HttpStatus status;

    public CustomExceptions(HttpStatus status, String message){
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
