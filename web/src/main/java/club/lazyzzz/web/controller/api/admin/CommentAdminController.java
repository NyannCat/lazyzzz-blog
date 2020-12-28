package club.lazyzzz.web.controller.api.admin;

import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.model.dto.AdminCommentDto;
import club.lazyzzz.web.model.entity.Comment;
import club.lazyzzz.web.service.ICommentService;
import club.lazyzzz.web.service.ITokenService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/comment")
public class CommentAdminController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private ITokenService tokenService;

    @GetMapping
    @ApiOperation("评论列表")
    public IPage<Comment> pageBy(@RequestParam Integer page, @RequestParam Integer size) {
        return commentService.page(new Page<>(page, size));
    }

    @PostMapping
    @ApiOperation("管理员回复评论")
    public Response comment(@RequestBody @Valid AdminCommentDto dto) {
        commentService.commentAdmin(dto);
        return Response.ok("评论成功", null);
    }

    @DeleteMapping
    @ApiOperation("删除评论")
    public Response delete(@RequestParam Integer id) {
        commentService.deleteById(id);
        return Response.ok("删除成功", null);
    }
}
