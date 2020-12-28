package club.lazyzzz.web.mapper;

import club.lazyzzz.web.model.entity.Comment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
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
public interface CommentMapper extends BaseMapper<Comment> {
    /**
     * 根据文章id查询所有评论
     *
     * @param articleId 文章id
     * @return 文章所属所有评论
     */
    List<Comment> selectByArticleId(@Param("articleId") Integer articleId);
}
