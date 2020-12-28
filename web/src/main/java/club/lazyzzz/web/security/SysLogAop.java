package club.lazyzzz.web.security;

import club.lazyzzz.web.mapper.SysLogMapper;
import club.lazyzzz.web.model.bo.IpDetail;
import club.lazyzzz.web.model.entity.SysLog;
import club.lazyzzz.web.security.annotation.SystemLog;
import club.lazyzzz.web.service.IIp2RegionService;
import club.lazyzzz.web.util.IpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SysLogAop {

    @Autowired
    private IIp2RegionService ip2RegionService;
    @Autowired
    private SysLogMapper logMapper;

    @After("@annotation(club.lazyzzz.web.security.annotation.SystemLog)")
    public void after(JoinPoint point) throws Throwable {
        String description = ((MethodSignature) point.getSignature())
                .getMethod()
                .getAnnotation(SystemLog.class)
                .value();

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ipAddress = IpUtil.getIpAddress(request);

        IpDetail detail = ip2RegionService.getDetail(ipAddress);
        SysLog sysLog = new SysLog();
        sysLog.setIp(ipAddress);
        sysLog.setCountry(detail.getCountry());
        sysLog.setProvince(detail.getProvince());
        sysLog.setCity(detail.getCity());
        sysLog.setDescription(description);
        logMapper.insert(sysLog);
    }
}
