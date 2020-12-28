package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.mapper.ArticleMapper;
import club.lazyzzz.web.mapper.CategoryMapper;
import club.lazyzzz.web.model.entity.Article;
import club.lazyzzz.web.model.entity.Category;
import club.lazyzzz.web.model.vo.ArchiveVO;
import club.lazyzzz.web.model.vo.ArticleVO;
import club.lazyzzz.web.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<ArchiveVO> getArchiveList() {
        List<ArticleVO> articles = baseMapper.selectArchiveList();
        List<ArchiveVO> archiveVOList = new ArrayList<>();
        int index = -1, prevYear = -1;
        for (ArticleVO article : articles) {
            int year = article.getCreateTime().getYear();
            if (prevYear != year) {
                ArchiveVO archiveVO = new ArchiveVO();
                archiveVO.setYear(year);
                archiveVO.setArticles(new ArrayList<>());
                archiveVO.getArticles().add(article);
                archiveVOList.add(archiveVO);
                prevYear = year;
                index++;
            } else {
                archiveVOList.get(index).getArticles().add(article);
            }
        }
        return archiveVOList;
    }

    @Override
    public boolean save(Article entity) {
        Category category = categoryMapper.selectById(entity.getCategoryId());
        Assert.notNull(category, "文章分类不存在");
        baseMapper.insert(entity);
        return true;
    }
}
