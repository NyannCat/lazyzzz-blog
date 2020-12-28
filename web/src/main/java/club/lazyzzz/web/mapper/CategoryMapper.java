package club.lazyzzz.web.mapper;

import club.lazyzzz.web.model.entity.Category;
import club.lazyzzz.web.model.vo.CategoryVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {
    /**
     * 获得全部分类视图
     * @return 分类视图列表
     */
    List<CategoryVO> selectCategoryVO();
}
