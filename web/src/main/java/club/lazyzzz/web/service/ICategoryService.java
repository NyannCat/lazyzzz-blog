package club.lazyzzz.web.service;

import club.lazyzzz.web.model.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
public interface ICategoryService extends IService<Category> {
    /**
     * 修改指定分类
     * @param categoryId 分类id
     */
    void updateCategory(Integer categoryId, String name);

    /**
     * 删除指定分类
     * @param categoryId 分类id
     */
    void removeCategory(Integer categoryId);
}
