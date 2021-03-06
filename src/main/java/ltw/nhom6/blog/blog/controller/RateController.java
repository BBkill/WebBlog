package ltw.nhom6.blog.blog.controller;


import ltw.nhom6.blog.blog.dto.request.RateBlogReqDto;
import ltw.nhom6.blog.blog.dto.response.RateBlogResDto;
import ltw.nhom6.blog.blog.service.RateService;
import ltw.nhom6.blog.core.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/blog/add-rate")
public class RateController {

    private final RateService service;

    @Autowired
    public RateController(RateService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public Result<RateBlogResDto> addRate(@RequestHeader("token") String token ,@RequestBody @Valid RateBlogReqDto reqDto) {
        return Result.success(service.rate(reqDto, token));
    }
}
