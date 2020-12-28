package club.lazyzzz.web.controller.api;

import club.lazyzzz.web.service.ICaptchaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Controller
public class CaptchaController {
    @Autowired
    private ICaptchaService captchaService;

    @GetMapping("/api/captcha")
    @ApiOperation("获取二维码")
    public void newCaptcha(HttpServletRequest request, HttpServletResponse response) {
        captchaService.newCaptcha(request, response);
    }
}
