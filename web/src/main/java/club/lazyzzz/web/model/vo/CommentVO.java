package club.lazyzzz.web.model.vo;

import club.lazyzzz.web.model.entity.Comment;
import lombok.Data;

import java.util.List;

@Data
public class CommentVO {
    private Comment comment;
    private List<Comment> reply;
}
