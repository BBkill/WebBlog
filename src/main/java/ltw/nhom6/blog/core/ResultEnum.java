package ltw.nhom6.blog.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(200, "OK"),

    LOGIN_SUCCESS(200, "LOGIN SUCCESS"),

    GET_OTP_CODE(200, "OTP SENT SUCCESS"),

    BLOG_CREATED(201, "BLOG CREATED SUCCESSFULLY"),

    USER_CREATED(201, "USER CREATED SUCCESSFULLY");

    private Integer code;

    private String desc;

}
