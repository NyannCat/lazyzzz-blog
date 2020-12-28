package club.lazyzzz.web.service;

import club.lazyzzz.web.model.dto.UserLoginDto;
import club.lazyzzz.web.model.dto.UserRegisterDto;
import club.lazyzzz.web.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
public interface IUserService extends IService<User> {
    /**
     * 用户注册
     *
     * @param dto 用户注册对象
     */
    void register(UserRegisterDto dto);

    /**
     * 用户登录
     *
     * @param dto 用户登录对象
     * @return token
     */
    String login(UserLoginDto dto);

    /**
     * 更新用户密码
     *
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     */
    void updatePassword(String oldPassword, String newPassword);
}
