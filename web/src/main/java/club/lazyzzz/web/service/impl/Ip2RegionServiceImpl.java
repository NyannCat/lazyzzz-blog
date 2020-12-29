package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.model.bo.IpDetail;
import club.lazyzzz.web.service.IIp2RegionService;
import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DataBlock;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Service
public class Ip2RegionServiceImpl implements IIp2RegionService {

    @Autowired
    private DbSearcher dbSearcher;

    @Override
    public IpDetail getDetail(@NonNull String ip) {
        /*
            原始数据： _城市Id
                     国家|区域|省份|城市|ISP_
            根据 | 进行拆分获得具体信息
            国家:0, 区域:1, 省份:2, 城市:3, ISP:4
         */
        IpDetail detail = null;
        try {
            DataBlock block = dbSearcher.memorySearch(ip);
            String region = block.getRegion();
            String[] split = region.split("\\|");
            detail = new IpDetail();
            //bean填充
            detail.setCityId(block.getCityId());
            detail.setCountry(split[0]);
            detail.setArea(split[1]);
            detail.setProvince(split[2]);
            detail.setCity(split[3]);
            detail.setIsp(split[4]);
        } catch (IOException e) {
            log.error("ip查询失败, ip地址 {}, 错误原因 {}", ip, e);
        }
        Assert.notNull(detail, "ip数据为空，查询失败");
        return detail;
    }
}
