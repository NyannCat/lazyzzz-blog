package club.lazyzzz.web.model.dto;

import club.lazyzzz.web.model.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;

@Data
@ApiModel(value = "用户更新对象")
public class UserUpdateDto implements InputConverter<User> {

    @ApiModelProperty("用户名")
    private String username;

    @Email
    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("头像")
    private String avatar;

    @ApiModelProperty("头像")
    private String description;
}
