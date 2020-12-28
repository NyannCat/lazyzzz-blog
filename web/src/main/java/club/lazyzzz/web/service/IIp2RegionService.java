package club.lazyzzz.web.service;

import club.lazyzzz.web.model.bo.IpDetail;

public interface IIp2RegionService {
    /**
     * 根据ip，返回详细信息
     * @param ip ip地址
     * @return 详细信息
     */
    IpDetail getDetail(String ip);
}
