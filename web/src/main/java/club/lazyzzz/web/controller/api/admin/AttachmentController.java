package club.lazyzzz.web.controller.api.admin;


import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.model.entity.Attachment;
import club.lazyzzz.web.model.enums.AttachmentType;
import club.lazyzzz.web.oss.UploadResult;
import club.lazyzzz.web.service.IAttachmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/api/admin/attachment")
public class AttachmentController {

    @Autowired
    private IAttachmentService attachmentService;

    @GetMapping
    @ApiOperation("分页查询附件")
    public IPage<Attachment> pageBy(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "10") Integer size,
                                    @RequestParam(required = false) String keyword) {
        QueryWrapper<Attachment> wrapper = new QueryWrapper<>();
        if (keyword != null) {
            wrapper.like("name", "%" + keyword + "%");
        }
        return attachmentService.page(new Page<>(page, size), wrapper);
    }

    @PostMapping
    @ApiOperation("上传附件")
    public UploadResult upload(@RequestBody MultipartFile file, @RequestParam AttachmentType type) {
        return attachmentService.upload(file, type);
    }

    @DeleteMapping
    @ApiOperation("删除附件")
    public Response delete(@RequestParam Integer id) {
        attachmentService.delete(id);
        return Response.ok("附件删除成功", null);
    }
}


