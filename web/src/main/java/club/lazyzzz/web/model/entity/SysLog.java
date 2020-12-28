package club.lazyzzz.web.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 *
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-26
 */
@Data
@TableName("sys_log")
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="SysLog", description="系统日志对象")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "ip地址, int类型存储, sql时需要转换")
    private String ip;

    @ApiModelProperty(value = "国家名称")
    private String country;

    @ApiModelProperty(value = "省份信息")
    private String province;

    @ApiModelProperty(value = "城市名称")
    private String city;

    @ApiModelProperty(value = "操作说明")
    private String description;

    @ApiModelProperty(value = "访问时间")
    private LocalDateTime createTime;


}
