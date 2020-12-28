package club.lazyzzz.web.config;

import lombok.extern.slf4j.Slf4j;
import org.lionsoul.ip2region.DbConfig;
import org.lionsoul.ip2region.DbMakerConfigException;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;

@Slf4j
@Component
public class Ip2RegionConfig {
    @Bean
    public DbSearcher dbSearcher() {
        DbSearcher dbSearcher = null;
        ClassLoader loader = this.getClass().getClassLoader();
        try (var input = loader.getResourceAsStream("vendor/ip2region.db")) {
            Assert.notNull(input, "获取数据库文件失败");
            dbSearcher = new DbSearcher(new DbConfig(), input.readAllBytes());
        } catch (IOException | DbMakerConfigException e) {
            log.error("ip2region 数据库加载失败", e);
        }
        Assert.notNull(dbSearcher, "数据库创建失败");
        return dbSearcher;
    }
}
