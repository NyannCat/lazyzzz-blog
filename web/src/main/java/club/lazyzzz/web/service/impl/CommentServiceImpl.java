package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.exception.RecordNotFoundException;
import club.lazyzzz.web.mapper.ArticleMapper;
import club.lazyzzz.web.mapper.CommentMapper;
import club.lazyzzz.web.model.dto.AdminCommentDto;
import club.lazyzzz.web.model.dto.CommentDto;
import club.lazyzzz.web.model.entity.Comment;
import club.lazyzzz.web.model.vo.CommentVO;
import club.lazyzzz.web.service.ICommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
    public void comment(CommentDto dto) {
        save(dto.convertTo(new Comment()));
        articleMapper.updateCommentsById(dto.getArticleId());
    }

    @Override
    public void commentAdmin(AdminCommentDto dto) {
        Comment comment = dto.convertTo(new Comment());
        comment.setIsAdmin(1);
        save(comment);
    }

    @Override
    public List<CommentVO> listCommentByArticleId(Integer articleId) {
        List<Comment> comments = baseMapper.selectByArticleId(articleId);
        //根据父类id排序
        comments.sort(Comparator.comparingInt(Comment::getParentId));
        List<CommentVO> result = new ArrayList<>();

        //数据按照parent_id升序排序
        //所以parentId != 0后面的数据都是评论回复
        int index = -1, prev = 0;
        for (Comment comment : comments) {
            int parentId = comment.getParentId();
            if (parentId == 0) {
                CommentVO vo = new CommentVO();
                vo.setComment(comment);
                vo.setReply(new ArrayList<>());
                result.add(vo);
            } else {
                //若评论回复的id与前一个回复id不同，下标++, 相同则维持原样
                if (parentId != prev) {
                    index++;
                    prev = parentId;
                }
                result.get(index).getReply().add(comment);
            }
        }
        return result;
    }

    @Override
    @Transactional
    public void deleteById(Integer id) {
        Comment comment = baseMapper.selectById(id);
        if (comment == null) {
            throw new RecordNotFoundException("评论不存在").setErrorData(id);
        }
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", comment.getId())
                .select("id");
        List<Integer> childIds = baseMapper.selectList(wrapper)
                .stream()
                .map(Comment::getId)
                .collect(Collectors.toList());
        if (!childIds.isEmpty()) {
            baseMapper.deleteBatchIds(childIds);
        }
        baseMapper.deleteById(id);
    }
}
