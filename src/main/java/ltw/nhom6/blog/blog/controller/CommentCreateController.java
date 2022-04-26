package ltw.nhom6.blog.blog.controller;

import ltw.nhom6.blog.blog.dto.request.CommentBlogReqDto;
import ltw.nhom6.blog.blog.dto.response.CommentBlogResDto;
import ltw.nhom6.blog.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/blog/add-comment")
public class CommentCreateController {

    private final CommentService service;

    @Autowired
    public CommentCreateController(CommentService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentBlogResDto addComment(@RequestBody @Valid CommentBlogReqDto reqDto) {
        return service.comment(reqDto);
    }
}
