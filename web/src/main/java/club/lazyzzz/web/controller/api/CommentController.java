package club.lazyzzz.web.controller.api;


import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.model.dto.CommentDto;
import club.lazyzzz.web.service.ICaptchaService;
import club.lazyzzz.web.service.ICommentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;
    @Autowired
    private ICaptchaService captchaService;

    @PostMapping
    @ApiOperation("发布评论")
    public Response comment(@RequestBody @Valid CommentDto dto, HttpServletRequest request) {
        Assert.isTrue(captchaService.verifyCaptcha(dto.getCaptcha(), request), "验证码不一致");
        commentService.comment(dto);
        return Response.ok("评论成功", null);
    }
}

