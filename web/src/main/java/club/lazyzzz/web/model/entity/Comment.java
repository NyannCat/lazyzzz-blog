package club.lazyzzz.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@ApiModel(value="Comment对象", description="")
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论人昵称")
    private String nickname;

    @ApiModelProperty(value = "评论人邮箱")
    private String email;

    @ApiModelProperty(value = "评论创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "文章外键引用")
    private Integer articleId;

    @ApiModelProperty(value = "评论父级引用")
    private Integer parentId;

    @ApiModelProperty(value = "是否为管理员评论0:不是,1:是")
    private Integer isAdmin;
}
