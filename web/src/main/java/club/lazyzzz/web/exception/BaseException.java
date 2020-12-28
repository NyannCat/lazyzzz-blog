package club.lazyzzz.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public abstract class BaseException extends RuntimeException {
    private Object errorData;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract HttpStatus getStatus();

    @Nullable
    public Object getErrorData() {
        return errorData;
    }

    public BaseException setErrorData(@Nullable Object errorData) {
        this.errorData = errorData;
        return this;
    }
}
