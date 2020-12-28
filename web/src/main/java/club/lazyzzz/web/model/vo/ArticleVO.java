package club.lazyzzz.web.model.vo;

import club.lazyzzz.web.model.entity.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ArticleVO extends Article {
    private String categoryName;
}
