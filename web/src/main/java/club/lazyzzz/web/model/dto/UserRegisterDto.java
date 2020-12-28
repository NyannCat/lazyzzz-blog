package club.lazyzzz.web.model.dto;

import club.lazyzzz.web.model.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@ApiModel(value = "用户dto对象")
public class UserRegisterDto implements InputConverter<User> {
    @NotBlank
    @ApiModelProperty("用户名")
    private String username;

    @NotBlank
    @ApiModelProperty("明文密码")
    private String password;

    @Email
    @NotBlank
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("头像")
    private String description;
}
