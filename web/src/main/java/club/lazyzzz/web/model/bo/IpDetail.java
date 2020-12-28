package club.lazyzzz.web.model.bo;

import lombok.Data;

@Data
public class IpDetail {
    private Integer cityId;
    private String country;
    private String area;
    private String province;
    private String city;
    private String isp;
}
