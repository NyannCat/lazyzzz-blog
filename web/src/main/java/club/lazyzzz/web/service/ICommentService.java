package club.lazyzzz.web.service;

import club.lazyzzz.web.model.dto.AdminCommentDto;
import club.lazyzzz.web.model.dto.CommentDto;
import club.lazyzzz.web.model.entity.Comment;
import club.lazyzzz.web.model.vo.CommentVO;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
public interface ICommentService extends IService<Comment> {

    /**
     * 评论文章
     * @param dto 评论dto
     */
    void comment(CommentDto dto);

    /**
     * 管理员评论文章
     * @param dto 评论dto
     */
    void commentAdmin(AdminCommentDto dto);

    /**
     * 根据文章id，选取评论
     * @param articleId 文章id
     */
    List<CommentVO> listCommentByArticleId(Integer articleId);

    /**
     * 递归删除评论
     * @param id 父级评论id
     */
    void deleteById(Integer id);
}
