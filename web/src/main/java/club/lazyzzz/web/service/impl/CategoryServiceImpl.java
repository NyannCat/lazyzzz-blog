package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.exception.RecordNotFoundException;
import club.lazyzzz.web.mapper.ArticleMapper;
import club.lazyzzz.web.mapper.CategoryMapper;
import club.lazyzzz.web.model.entity.Article;
import club.lazyzzz.web.model.entity.Category;
import club.lazyzzz.web.service.ICategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void updateCategory(Integer categoryId, String name) {
        Category category = baseMapper.selectById(categoryId);
        if ("未分类".equals(category.getName())) {
            throw new RecordNotFoundException("不可以修改默认分类");
        }
        category.setName(name);
        baseMapper.updateById(category);
    }

    @Override
    @Transactional
    public void removeCategory(Integer categoryId) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "未分类");
        Category defaultCategory = baseMapper.selectOne(wrapper);

        if (categoryId.equals(defaultCategory.getId())) {
            throw new RecordNotFoundException("不可以删除默认分类");
        }

        QueryWrapper<Article> articleWrapper = new QueryWrapper<>();
        articleWrapper.select("id", "category_id").eq("category_id", categoryId);
        List<Article> articles = articleMapper.selectList(articleWrapper);
        if (!articles.isEmpty()) {
            articles.forEach(article -> {
                article.setCategoryId(defaultCategory.getId());
                articleMapper.updateById(article);
            });
        }
        baseMapper.deleteById(categoryId);
    }
}
