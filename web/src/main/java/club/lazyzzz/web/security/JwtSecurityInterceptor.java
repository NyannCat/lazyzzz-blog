package club.lazyzzz.web.security;

import club.lazyzzz.web.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtSecurityInterceptor implements HandlerInterceptor {

    private static final String KEY_HEADER = "Authorization";

    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        String header = request.getHeader(KEY_HEADER);
        tokenService.verify(header);
        return true;
    }
}
