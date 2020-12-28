package club.lazyzzz.web.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("用户登录模型")
public class UserLoginDto {
    @NotBlank
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank
    @ApiModelProperty("明文密码")
    private String password;
}
