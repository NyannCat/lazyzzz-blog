package club.lazyzzz.web.controller.api.admin;

import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.mapper.UserMapper;
import club.lazyzzz.web.model.dto.UserRegisterDto;
import club.lazyzzz.web.service.IInstallService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/install")
public class InstallController {
    @Autowired
    private IInstallService installService;
    @Autowired
    private UserMapper userMapper;

    @PostMapping
    @ApiOperation("博客安装")
    public Response install(@RequestBody @Valid UserRegisterDto dto) {
        installService.install(dto);
        return Response.ok("安装成功", null);
    }

    @GetMapping
    @ApiOperation("检测博客是否已经安装")
    public Response isInstalled() {
        Integer integer = userMapper.selectCount(null);
        return Response.ok(integer > 0);
    }
}
