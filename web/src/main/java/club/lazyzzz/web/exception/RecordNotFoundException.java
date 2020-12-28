package club.lazyzzz.web.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends BaseException {

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
