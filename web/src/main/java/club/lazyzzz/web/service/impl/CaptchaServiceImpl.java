package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.service.ICaptchaService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Slf4j
@Service
public class CaptchaServiceImpl implements ICaptchaService {

    private static final String CAPTCHA_SESSION_KEY = "captcha";

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @Override
    public void newCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);

        request.getSession().setAttribute(CAPTCHA_SESSION_KEY, text);

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        try (var out = response.getOutputStream()) {
            ImageIO.write(image, "jpg", out);
            out.flush();
        } catch (IOException e) {
            log.warn("验证码生成I/O异常", e);
        }
    }

    @Override
    public boolean verifyCaptcha(String plainText, HttpServletRequest request) {
        String attribute = (String) request.getSession().getAttribute(CAPTCHA_SESSION_KEY);
        return plainText.equalsIgnoreCase(attribute);
    }
}
