package club.lazyzzz.web.security;

import club.lazyzzz.web.mapper.SysLogMapper;
import club.lazyzzz.web.model.bo.IpDetail;
import club.lazyzzz.web.model.entity.SysLog;
import club.lazyzzz.web.security.annotation.SystemLog;
import club.lazyzzz.web.service.IIp2RegionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class SysLogAop {

    @Autowired
    private IIp2RegionService ip2RegionService;
    @Autowired
    private SysLogMapper logMapper;

    @Around("@annotation(club.lazyzzz.web.security.annotation.SystemLog)")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = point.proceed();
        String description = ((MethodSignature) point.getSignature())
                .getMethod()
                .getAnnotation(SystemLog.class)
                .value();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String remoteAddr = attributes.getRequest().getRemoteAddr();
        if ("0:0:0:0:0:0:0:1".equals(remoteAddr)) {
            remoteAddr = "127.0.0.1";
        }
        IpDetail detail = ip2RegionService.getDetail(remoteAddr);
        SysLog sysLog = new SysLog();
        sysLog.setIp(remoteAddr);
        sysLog.setCountry(detail.getCountry());
        sysLog.setCity(detail.getCity());
        sysLog.setDescription(description);
        logMapper.insert(sysLog);
        return result;
    }
}
