package club.lazyzzz.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Article对象", description="")
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "原始文章内容(Markdown)")
    private String originalContent;

    @ApiModelProperty(value = "格式化后文章内容(HTML)")
    private String formatContent;

    @ApiModelProperty(value = "访问数量")
    private Long views;

    @ApiModelProperty(value = "点赞数量")
    private Long likes;

    @ApiModelProperty(value = "分享数量")
    private Long shares;

    @ApiModelProperty(value = "评论数量")
    private Long comments;

    @ApiModelProperty(value = "0:公开,1:私密")
    private Integer type;

    @ApiModelProperty(value = "文章创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "文章更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "文章封面url")
    private String coverPage;

    @ApiModelProperty(value = "文章类型外键")
    private Integer categoryId;

    @ApiModelProperty(value = "文章预览")
    private String summary;
}
