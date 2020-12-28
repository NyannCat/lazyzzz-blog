package club.lazyzzz.web;

import club.lazyzzz.web.service.IIp2RegionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Ip2RegionTest {

    @Autowired
    private IIp2RegionService ip2RegionService;

    @Test
    public void detailTest() {
        String ip = "120.36.203.239";
        System.out.println(ip2RegionService.getDetail(ip));
    }
}
