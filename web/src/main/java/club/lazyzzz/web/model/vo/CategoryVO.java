package club.lazyzzz.web.model.vo;

import club.lazyzzz.web.model.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryVO extends Category {
    private Integer count;
}
