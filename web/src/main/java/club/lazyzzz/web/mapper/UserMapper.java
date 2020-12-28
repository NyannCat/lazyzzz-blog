package club.lazyzzz.web.mapper;

import club.lazyzzz.web.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 获取用户创建时间
     *
     * @param id 用户id
     * @return 创建时间
     */
    LocalDateTime selectCreateTimeById(@Param("id") Integer id);
}
