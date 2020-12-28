package club.lazyzzz.web.exception;

import org.springframework.http.HttpStatus;

public class SecurityException extends BaseException {

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.FORBIDDEN;
    }
}
