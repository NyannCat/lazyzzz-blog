package club.lazyzzz.web.oss;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.http.MediaType;

@Data
@ApiModel("文件上传返回结果")
public class UploadResult {
    @ApiModelProperty("文件名")
    private String filename;

    @ApiModelProperty("文件路径")
    private String filePath;

    @ApiModelProperty("文件大小")
    private Long size;

    @ApiModelProperty("宽度")
    private Integer width;

    @ApiModelProperty("高度")
    private Integer height;

    @ApiModelProperty("文件类型")
    private MediaType mediaType;

    @ApiModelProperty("文件唯一编码")
    private String key;
}
