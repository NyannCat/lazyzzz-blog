package club.lazyzzz.web.core;

import club.lazyzzz.web.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("club.lazyzzz.web.controller.api")
public class ExceptionAdvice {

    @ExceptionHandler(RuntimeException.class)
    public Response handleRuntimeException(RuntimeException e) {
        return buildResponse(e);
    }

    @ExceptionHandler(BaseException.class)
    public Response handleBaseException(BaseException e) {
        return buildResponse(e);
    }

    private Response buildResponse(RuntimeException e) {
        Response response = new Response();
        response.setMsg(e.getMessage());
        if (e instanceof BaseException) {
            BaseException ex = (BaseException) e;
            response.setCode(ex.getStatus().value());
            response.setData(ex.getErrorData());
        } else {
            response.setCode(HttpStatus.BAD_REQUEST.value());
        }
        return response;
    }
}
