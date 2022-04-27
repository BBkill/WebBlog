package ltw.nhom6.blog.core;

import lombok.Data;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Log4j2
@Data
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1232424234L;

    public static final String SUCCESSFUL_MESSAGE = "SUCCESS";

    private Integer code = ResultEnum.SUCCESS.getCode();

    private Object other;

    private String msg = null;

    private T data = null;

    public Result() {
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(int code, Object other, String msg, T data) {
        this.code = code;
        this.other = other;
        this.msg = msg;
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(ResultEnum resultEnum) {
        this.code = resultEnum.getCode();
        this.msg = resultEnum.getDesc();
    }


    public Result(int code, String msg, T data, Object other) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.other = other;
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), SUCCESSFUL_MESSAGE, data);
    }

    public static <T> Result<T> createCommentSuccess(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), SUCCESSFUL_MESSAGE, data);
    }

    public static <T> Result<T> createSuccess(T data) {
        return new Result<>(ResultEnum.BLOG_CREATED.getCode(), SUCCESSFUL_MESSAGE, data);
    }

    public static <T> Result<T> loginSuccess(T data) {
        return new Result<>(ResultEnum.LOGIN_SUCCESS.getCode(), SUCCESSFUL_MESSAGE, data);
    }

    public static <T> Result<T> creatUserSuccess(T data) {
        return new Result<>(ResultEnum.LOGIN_SUCCESS.getCode(), SUCCESSFUL_MESSAGE, data);
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultEnum.SUCCESS.getCode(), SUCCESSFUL_MESSAGE);
    }

    @Override
    public String toString() {
        return "Result [code=" + code + ", msg=" + msg + ", data=" + data + "]";
    }

}
