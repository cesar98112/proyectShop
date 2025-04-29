package api.authentication.exeption;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.function.ServerRequest;


import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest request, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(request,options);
        Throwable throwable = getError(request);
        if(throwable instanceof CustomExceptions) {
            CustomExceptions customException = (CustomExceptions) throwable;
            errorAttributes.put("status", customException.getStatus());
            errorAttributes.put("message", customException.getMessage());
        }
        return errorAttributes;
    }
}
