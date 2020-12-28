import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.jupiter.api.Test;

public class CodeGenerator {

    public void codeGenerator() {
        AutoGenerator generator = new AutoGenerator();
        //全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setAuthor("lazyzzz");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setSwagger2(true);
        generator.setGlobalConfig(globalConfig);

        //DataSource配置
        DataSourceConfig dataConfig = new DataSourceConfig();
        dataConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataConfig.setDbType(DbType.MYSQL);
        dataConfig.setUrl("jdbc:mysql://localhost:3306/blog?useSSL=false&serverTimezone=Asia/Shanghai");
        dataConfig.setUsername("root");
        dataConfig.setPassword("toor");
        generator.setDataSource(dataConfig);

        //包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName("web");
        packageConfig.setParent("club.lazyzzz");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setController("controller");
        packageConfig.setServiceImpl("service");
        generator.setPackageInfo(packageConfig);

        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("sys_log");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setRestControllerStyle(true);
        strategy.setEntityLombokModel(true);
        generator.setStrategy(strategy);

        generator.execute();
    }

}
