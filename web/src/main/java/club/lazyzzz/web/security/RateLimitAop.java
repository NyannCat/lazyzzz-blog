package club.lazyzzz.web.security;

import club.lazyzzz.web.security.annotation.RateLimit;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Aspect
@Component
public class RateLimitAop {

    private final Map<String, RateLimiter> limiterMap = new ConcurrentHashMap<>(4);

    @Before("@annotation(club.lazyzzz.web.security.annotation.RateLimit)")
    public void limit(JoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String className = method.getDeclaringClass().getName();

        String key = className + "." + method.getName();
        if (!limiterMap.containsKey(key)) {
            double qps = method.getAnnotation(RateLimit.class).value();
            limiterMap.put(key, RateLimiter.create(qps));
        } else {
            if (!limiterMap.get(key).tryAcquire()) {
                log.warn("接口方法 {} 频繁访问", key);
                throw new SecurityException("接口频繁请求，请稍后再试");
            }
        }
    }
}
