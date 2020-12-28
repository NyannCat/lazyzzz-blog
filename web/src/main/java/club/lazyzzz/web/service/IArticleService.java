package club.lazyzzz.web.service;

import club.lazyzzz.web.model.entity.Article;
import club.lazyzzz.web.model.vo.ArchiveVO;
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
public interface IArticleService extends IService<Article> {
    /**
     * 获得归档VO
     * @return 归档VO
     */
    List<ArchiveVO> getArchiveList();
}
