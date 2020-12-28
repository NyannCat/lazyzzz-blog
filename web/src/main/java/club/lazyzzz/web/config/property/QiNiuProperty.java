package club.lazyzzz.web.config.property;

import lombok.Data;

@Data
public class QiNiuProperty {
    private String accessKey;
    private String secretKey;
    private String bucketName;
    private String domain;
    private String style;
}
