package ltw.nhom6.blog.exception;

import org.springframework.http.HttpStatus;

public class AbstractException extends RuntimeException{
    private static final long serialVersionUID = -324324234l;

    private final Object errorMessage;
    private final HttpStatus httpStatus;

    public AbstractException(Object errorMessage, HttpStatus httpStatus) {
        super();
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
