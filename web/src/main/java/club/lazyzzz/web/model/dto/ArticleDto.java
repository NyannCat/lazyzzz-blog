package club.lazyzzz.web.model.dto;

import club.lazyzzz.web.model.entity.Article;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("文章上传模型")
public class ArticleDto implements InputConverter<Article> {

    @ApiModelProperty("文章id")
    private Integer id;

    @ApiModelProperty("文章标题")
    @NotBlank(message = "文章标题不能为空")
    private String title;

    @ApiModelProperty("文章Markdown内容")
    @NotBlank(message = "文章内容不能为空")
    private String originalContent;

    @ApiModelProperty("文章HTML内容")
    @NotBlank(message = "HTML内容不能为空")
    private String formatContent;

    @ApiModelProperty("文章类型, 0:公开,1:私密")
    @NotNull(message = "文章类型不能为空")
    private Integer type;

    @ApiModelProperty("文章简介")
    @NotBlank(message = "文章简介不能为空")
    private String summary;

    @ApiModelProperty("文章封面")
    @NotBlank(message = "文章封面不能为空")
    private String coverPage;

    @NotNull
    @ApiModelProperty("文章分类id")
    private Integer categoryId;
}
