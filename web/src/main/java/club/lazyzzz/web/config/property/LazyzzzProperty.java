package club.lazyzzz.web.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.io.File;

@Data
@ConfigurationProperties("lazyzzz")
public class LazyzzzProperty {
    private String adminPath = "static/admin";
    private String workDir = System.getProperty("user.home") + File.separator + "lazyzzz" + File.separator;
    private String uploadDir = workDir + "upload" + File.separator;

    //七牛云配置
    @NestedConfigurationProperty
    private QiNiuProperty qiNiuProperty;
}
