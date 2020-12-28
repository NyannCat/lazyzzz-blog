package club.lazyzzz.web.controller;

import club.lazyzzz.web.mapper.ArticleMapper;
import club.lazyzzz.web.mapper.CategoryMapper;
import club.lazyzzz.web.mapper.UserMapper;
import club.lazyzzz.web.service.IArticleService;
import club.lazyzzz.web.service.ICommentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private ICommentService commentService;

    @GetMapping("/")
    public String index(Model model) {
        return this.index(1, model);
    }

    @GetMapping("/page")
    public String index(@RequestParam Integer page, Model model) {
        model.addAttribute("vo", articleMapper.selectArticleVO(new Page<>(page, 10)));
        model.addAttribute("hottest", articleMapper.selectHottestArticle(10));
        model.addAttribute("categories", categoryMapper.selectCategoryVO());
        try {
            model.addAttribute("user", userMapper.selectList(new QueryWrapper<>()).get(0));
        } catch (IndexOutOfBoundsException e) {
            return "redirect:/admin/index.html#/install";
        }
        return "index";
    }

    @GetMapping("/article/{id}")
    public String article(@PathVariable Integer id, Model model) {
        model.addAttribute("article", articleMapper.selectArticleVOById(id));
        model.addAttribute("commentVOS", commentService.listCommentByArticleId(id));
        model.addAttribute("user", userMapper.selectById(1));
        articleMapper.updateViewsById(id);
        return "article";
    }

    @GetMapping("/search")
    public String search(@RequestParam String keyword, Model model) {
        return search(keyword, 1, model);
    }

    @GetMapping("/search/page")
    public String search(@RequestParam String keyword, @RequestParam Integer page, Model model) {
        model.addAttribute("vo", articleMapper.selectArticleVOByKeyword(new Page<>(page, 10), keyword));
        model.addAttribute("user", userMapper.selectList(new QueryWrapper<>()).get(0));
        return "search";
    }

    @GetMapping("/archive")
    public String archive(Model model) {
        model.addAttribute("count", articleService.count());
        model.addAttribute("archives", articleService.getArchiveList());
        return "archive";
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/index.html";
    }
}
