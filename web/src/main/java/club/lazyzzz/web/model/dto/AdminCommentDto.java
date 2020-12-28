package club.lazyzzz.web.model.dto;

import club.lazyzzz.web.model.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("管理员评论参数")
public class AdminCommentDto implements InputConverter<Comment> {
    @NotBlank(message = "评论内容不能为空")
    @Size(min = 1)
    @ApiModelProperty("评论内容")
    private String content;

    @NotNull
    @ApiModelProperty("文章ID")
    private Integer articleId;

    @NotNull
    @ApiModelProperty("父级评论ID")
    private Integer parentId;
}
