package ltw.nhom6.blog.blog.controller;

import ltw.nhom6.blog.blog.dto.request.BlogEditReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogEditResponse;
import ltw.nhom6.blog.blog.service.BlogEditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/blog/edit-blog")
public class BlogEditController {
    private final BlogEditService service;

    @Autowired
    public BlogEditController(BlogEditService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public BlogEditResponse execute(@Valid @RequestBody BlogEditReqDto reqDto) {
        return service.execute(reqDto);
    }
}
