package club.lazyzzz.web.model.dto;

import club.lazyzzz.web.model.entity.Comment;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@ApiModel("评论参数")
public class CommentDto implements InputConverter<Comment> {
    @NotBlank
    @ApiModelProperty("评论内容")
    private String content;

    @NotBlank
    @ApiModelProperty("评论人昵称")
    @Size(max = 255, message = "昵称过长")
    private String nickname;

    @Email
    @NotBlank
    @ApiModelProperty("评论人邮箱")
    private String email;

    @NotNull
    @ApiModelProperty("文章ID")
    private Integer articleId;

    @NotBlank
    @ApiModelProperty("验证码")
    private String captcha;

    @ApiModelProperty("父级评论ID")
    private Integer parentId;
}
