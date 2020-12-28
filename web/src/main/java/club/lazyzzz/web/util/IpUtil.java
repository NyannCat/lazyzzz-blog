package club.lazyzzz.web.util;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Pattern;

public class IpUtil {

    private static final String[] HEADERS = {
            "X-Forwarded-For",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "X-Real-IP"
    };

    private static final Pattern pattern = Pattern.
            compile("\\A(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)(\\.(25[0-5]|2[0-4]\\d|[0-1]?\\d?\\d)){3}\\z");

    /**
     * 判断ip是否为空，空返回true
     *
     * @param ip
     * @return 是否为空
     */
    public static boolean isEmptyIp(final String ip) {
        return (ip == null || ip.length() == 0 || ip.trim().equals("") || "unknown".equalsIgnoreCase(ip));
    }

    /***
     * 获取客户端ip地址(可以穿透代理)
     * @param request HttpServletRequest
     * @return ip地址
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = "";
        for (String header : HEADERS) {
            ip = request.getHeader(header);
            if (!isEmptyIp(ip)) {
                break;
            }
        }
        if (isEmptyIp(ip)) {
            ip = request.getRemoteAddr();
        }
        if (!isEmptyIp(ip) && ip.contains(",")) {
            ip = ip.split(",")[0];
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }

        return isIpv4(ip) ? ip : "0.0.0.0";
    }

    public static boolean isIpv4(String ipAddr) {
        return pattern.matcher(ipAddr).matches();
    }
}
