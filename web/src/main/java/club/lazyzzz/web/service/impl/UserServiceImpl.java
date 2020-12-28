package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.exception.UserException;
import club.lazyzzz.web.mapper.UserMapper;
import club.lazyzzz.web.model.dto.UserLoginDto;
import club.lazyzzz.web.model.dto.UserRegisterDto;
import club.lazyzzz.web.model.entity.User;
import club.lazyzzz.web.security.annotation.RateLimit;
import club.lazyzzz.web.security.annotation.SystemLog;
import club.lazyzzz.web.service.ITokenService;
import club.lazyzzz.web.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private ITokenService tokenService;

    @Override
    public void register(UserRegisterDto dto) {
        int count = count();
        if (count > 0) {
            throw new UserException("已存在管理员账户，不可重复注册");
        }
        User user = dto.convertTo(new User());
        if (user.getAvatar() == null || "".equalsIgnoreCase(user.getAvatar())) {
            user.setAvatar("/img/default-avatar.jpg");
        }
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        save(user);
    }

    @Override
    public String login(UserLoginDto dto) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", dto.getUsername());

        final User one = getOne(wrapper);
        if (one == null || !BCrypt.checkpw(dto.getPassword(), one.getPassword())) {
            log.warn("用户[{}]尝试密码[{}]登陆失败", dto.getUsername(), dto.getPassword());
            throw new UserException("用户名或密码错误");
        }

        String token = tokenService.generator(one.getId());
        log.info("用户[{}]登录成功, 生成token[{}]", one.getUsername(), token);

        return token;
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword) {
        User user = baseMapper.selectById(1);
        if (BCrypt.checkpw(oldPassword, user.getPassword())) {
            user.setPassword(BCrypt.hashpw(newPassword, BCrypt.gensalt()));
            baseMapper.updateById(user);
        } else {
            throw new UserException("旧密码不一致");
        }
    }
}
