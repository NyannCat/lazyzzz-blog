package club.lazyzzz.web.model.entity;

import club.lazyzzz.web.model.enums.AttachmentType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

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
@ApiModel(value="Attachment对象", description="")
public class Attachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "文件名称")
    private String name;

    @ApiModelProperty(value = "文件路径")
    private String path;

    @ApiModelProperty(value = "oss或本地文件key，用于删除文件")
    private String fileKey;

    @ApiModelProperty(value = "文件类型")
    private String mediaType;

    @ApiModelProperty(value = "文件存储类型(0：本地，1：七牛云)")
    private AttachmentType attachmentType;
}
