package club.lazyzzz.web.mapper;

import club.lazyzzz.web.model.entity.Article;
import club.lazyzzz.web.model.vo.ArticleVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Repository
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 分页查询，返回首页文章视图
     *
     * @param page 分页查询条件
     * @return 分页对象
     */
    IPage<ArticleVO> selectArticleVO(@Param("page") Page<ArticleVO> page);

    /**
     * 分页查询，按照文章名称，返回首页文章视图
     *
     * @param page    分页查询条件
     * @param keyword 标题名称
     * @return 分页对象
     */
    IPage<ArticleVO> selectArticleVOByKeyword(@Param("page") Page<ArticleVO> page,
                                              @Param("keyword") String keyword);

    /**
     * 选取归档文章信息
     *
     * @return 文章列表
     */
    List<ArticleVO> selectArchiveList();

    /**
     * 统计阅读量
     *
     * @return 阅读量
     */
    int selectTotalViews();

    /**
     * 文章详情页面
     *
     * @param id 文章id
     * @return 文章详情页对象
     */
    ArticleVO selectArticleVOById(@Param("id") Integer id);

    /**
     * 热门文章，根据访问量排序
     *
     * @param topK 前几
     */
    List<ArticleVO> selectHottestArticle(@Param("topK") int topK);

    /**
     * 根据文章分类选取文章
     *
     * @param categoryId 分类id
     */
    List<ArticleVO> selectByCategoryId(@Param("categoryId") Integer categoryId);

    /**
     * 评论数量+1
     *
     * @param id 文章id
     * @return 修改成功个数
     */
    int updateCommentsById(@Param("id") Integer id);

    /**
     * 浏览数量+1
     *
     * @param id 文章id
     * @return 修改成功个数
     */
    int updateViewsById(@Param("id") Integer id);
}
