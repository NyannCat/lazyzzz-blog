package club.lazyzzz.web.controller.api.admin;


import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.mapper.ArticleMapper;
import club.lazyzzz.web.model.dto.ArticleDto;
import club.lazyzzz.web.model.entity.Article;
import club.lazyzzz.web.model.vo.ArticleVO;
import club.lazyzzz.web.security.annotation.SystemLog;
import club.lazyzzz.web.service.IArticleService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/api/admin/article")
public class ArticleController {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private IArticleService articleService;

    @GetMapping
    @ApiOperation("根据id查找文章")
    public Article getById(@RequestParam Integer id) {
        return articleMapper.selectById(id);
    }

    @GetMapping("/all")
    @ApiOperation("分页查询文章列表")
    public IPage<ArticleVO> pageBy(@RequestParam(defaultValue = "1") Integer page,
                                   @RequestParam(defaultValue = "10") Integer limit) {
        return articleMapper.selectArticleVO(new Page<>(page, limit));
    }

    @PostMapping
    @ApiOperation("创建文章")
    public Response post(@RequestBody @Valid ArticleDto dto) {
        articleService.saveOrUpdate(dto.convertTo(new Article()));
        return Response.ok("文章上传成功", null);
    }

    @PutMapping
    @ApiOperation("更新文章")
    public Response put(@RequestBody @Valid ArticleDto dto, @RequestParam Integer id) {
        Article article = dto.convertTo(new Article());
        article.setId(id);
        articleService.updateById(article);
        return Response.ok("文章更新成功", null);
    }

    @DeleteMapping
    @ApiOperation("删除文章")
    public Response delete(@RequestParam Integer id) {
        articleService.removeById(id);
        return Response.ok("文章删除成功", null);
    }
}

