package club.lazyzzz.web.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class ArchiveVO {
    private int year;
    private List<ArticleVO> articles;
}
