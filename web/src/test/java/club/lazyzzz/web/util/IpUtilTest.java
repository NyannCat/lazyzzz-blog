package club.lazyzzz.web.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IpUtilTest {

    @Test
    void isIpv4() {
        String str = "1.1.1.0";
        Assertions.assertTrue(IpUtil.isIpv4(str));
        str = "0:0:0:0:0:0:0:1";
        Assertions.assertFalse(IpUtil.isIpv4(str));
        str = "unknown";
        Assertions.assertFalse(IpUtil.isIpv4(str));
    }
}
