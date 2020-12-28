package club.lazyzzz.web.config;

import club.lazyzzz.web.config.property.LazyzzzProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(LazyzzzProperty.class)
public class LazyzzzConfig {

    @Autowired
    private LazyzzzProperty property;
}
