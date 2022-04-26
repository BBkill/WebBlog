package ltw.nhom6.blog.blog.controller;

import ltw.nhom6.blog.blog.dto.request.BlogFindingReqDto;
import ltw.nhom6.blog.blog.dto.response.BlogResponse;
import ltw.nhom6.blog.blog.service.BlogFindingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogFindingService service;

    @Autowired
    public BlogController(BlogFindingService service) {
        this.service = service;
    }

    @GetMapping

    public ResponseEntity<BlogResponse> getAllBlog(@RequestBody BlogFindingReqDto reqDto) {
        return ResponseEntity.ok(service.findAllBlog(reqDto));
    }
}
