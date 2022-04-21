package ltw.nhom6.blog.exception.common;

import ltw.nhom6.blog.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class SystemException extends AbstractException {

    private static final long serialVersionUID = 27743248127813L;
    public SystemException(Object errorMessage, HttpStatus httpStatus) {
        super(errorMessage, httpStatus);
    }
}
