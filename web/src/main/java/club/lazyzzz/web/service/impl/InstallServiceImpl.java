package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.mapper.ArticleMapper;
import club.lazyzzz.web.model.dto.UserRegisterDto;
import club.lazyzzz.web.model.entity.Article;
import club.lazyzzz.web.model.entity.Category;
import club.lazyzzz.web.service.IArticleService;
import club.lazyzzz.web.service.ICategoryService;
import club.lazyzzz.web.service.IInstallService;
import club.lazyzzz.web.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstallServiceImpl implements IInstallService {

    @Autowired
    private IUserService userService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICategoryService categoryService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void install(UserRegisterDto dto) {
        userService.register(dto);
        //默认分类
        Category category = new Category();
        category.setId(1);
        category.setName("未分类");
        categoryService.save(category);
        //默认文章
        Article article = new Article();
        article.setCategoryId(1);
        article.setTitle("欢迎使用Lazyzzz博客");
        article.setOriginalContent("# 欢迎使用Lazyzzz博客\n" +
                "删除这篇文章，开始你的博客之旅吧");
        article.setFormatContent("<h1><a id=\"Lazyzzz_0\"></a>欢迎使用Lazyzzz博客</h1> <p>删除这篇文章，开始你的博客之旅吧</p>");
        article.setCoverPage("img/default-cover.jpg");
        articleService.save(article);
    }
}
