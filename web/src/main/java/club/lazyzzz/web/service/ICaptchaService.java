package club.lazyzzz.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lazyzzz
 * @since 2020-12-17
 */
public interface ICaptchaService {
    /**
     * 生成验证码
     *
     * @param request 请求体
     * @param response 响应体
     */
    void newCaptcha(HttpServletRequest request, HttpServletResponse response);

    /**
     * 校验验证码
     *
     * @param plainText 待校验输入
     * @param request 请求体
     * @return 验证结果
     */
    boolean verifyCaptcha(String plainText,HttpServletRequest request);
}
