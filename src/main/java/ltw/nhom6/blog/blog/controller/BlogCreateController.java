package ltw.nhom6.blog.blog.controller;

import ltw.nhom6.blog.blog.dto.request.BlogCreateReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogCreateResDto;
import ltw.nhom6.blog.blog.service.BlogCreateService;
import ltw.nhom6.blog.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/blog/create-blog")
public class BlogCreateController {
    private final BlogCreateService service;

    @Autowired
    public BlogCreateController(BlogCreateService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Result<BlogCreateResDto> execute(@RequestHeader("token") String token ,@Valid @RequestBody BlogCreateReqDto reqDto) {
        return Result.createSuccess(service.execute(reqDto, token));
    }
}
