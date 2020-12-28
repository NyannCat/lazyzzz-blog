package club.lazyzzz.web.exception;

import org.springframework.http.HttpStatus;

public class UserException extends BaseException {
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }
}
