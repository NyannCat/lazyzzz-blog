package club.lazyzzz.web.controller.api.admin;


import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.model.dto.UserLoginDto;
import club.lazyzzz.web.model.dto.UserRegisterDto;
import club.lazyzzz.web.model.dto.UserUpdateDto;
import club.lazyzzz.web.model.entity.User;
import club.lazyzzz.web.security.annotation.RateLimit;
import club.lazyzzz.web.security.annotation.SystemLog;
import club.lazyzzz.web.service.ITokenService;
import club.lazyzzz.web.service.IUserService;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

/**
 * <p>
 * 用户相关api控制器
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private ITokenService tokenService;

    @GetMapping
    @ApiOperation("获取用户信息")
    public User info(String token) {
        DecodedJWT jwt = tokenService.verify(token);
        Integer userId = jwt.getClaims().get("userId").asInt();
        return userService.getById(userId);
    }

    @PostMapping("/register")
    @SystemLog("用户注册")
    @ApiOperation("用户注册")
    public void register(@RequestBody @Valid UserRegisterDto dto) {
        userService.register(dto);
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    @RateLimit(value = 0.5)
    @SystemLog(value = "用户登录")
    public Response login(@RequestBody @Valid UserLoginDto dto) {
        String token = userService.login(dto);
        return Response.ok(new HashMap<String, String>(1) {{
            put("token", token);
        }});
    }

    @PutMapping
    @SystemLog("用户信息更新")
    @ApiOperation("更新用户信息")
    public Response update(@RequestBody @Valid UserUpdateDto dto) {
        User user = dto.convertTo(new User());
        user.setId(1);
        userService.updateById(user);
        return Response.ok("用户信息更新成功", null);
    }

    @PutMapping("/password")
    @SystemLog("用户密码修改")
    @ApiOperation("修改用户密码")
    public Response password(@RequestParam String oldPassword, @RequestParam String newPassword) {
        userService.updatePassword(oldPassword, newPassword);
        return Response.ok("密码更新成功", null);
    }
}

