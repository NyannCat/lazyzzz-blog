package club.lazyzzz.web.mapper;

import club.lazyzzz.web.model.entity.SysLog;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-26
 */
@Repository
public interface SysLogMapper {
    /**
     * 保存日志
     * @param sysLog 日志
     * @return 成功个数
     */
    int insert(SysLog sysLog);

    /**
     * 分页查询日志
     * @param page 分页参数
     * @return 分页对象
     */
    IPage<SysLog> selectPage(Page<SysLog> page);
}
