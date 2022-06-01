package ltw.nhom6.blog.blog.controller;

import ltw.nhom6.blog.blog.dto.response.page.PagingBlog;
import ltw.nhom6.blog.blog.service.BlogFindingService;
import ltw.nhom6.blog.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/blogs")
public class BlogController {

    private final BlogFindingService service;

    @Autowired
    public BlogController(BlogFindingService service) {
        this.service = service;
    }

    @GetMapping("/home")
    @Cacheable("all")
    public Result<PagingBlog> getPagingBlog(@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
        return Result.success(service.findBlogByPageSizeAndPageNumber(pageSize, pageNumber));
    }

    @GetMapping("/user")
    @Cacheable(value = "author", key = "#author")
    public Result<PagingBlog> getPagingBlogOfUser(@RequestParam(value = "author", defaultValue = "") String author,
                                @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
        return Result.success(service.findBlogByAuthorAndPageSizeAndPageNumber(author, pageSize, pageNumber));
    }

    @GetMapping("/search")
    @CacheEvict(value = "keyWord", key = "#keyWord")
    public Result<PagingBlog> getPagingBlogLike(@RequestParam(value = "keyWord", defaultValue = "all") String keyWord,
                                            @RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
                                            @RequestParam(value = "pageNumber", defaultValue = "1") int pageNumber) {
        return Result.success(service.findBlogByKeyWordAndPageSizeAndPageNumber(keyWord, pageSize, pageNumber));
    }
}
