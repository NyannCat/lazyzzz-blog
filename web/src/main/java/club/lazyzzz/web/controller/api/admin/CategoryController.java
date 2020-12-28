package club.lazyzzz.web.controller.api.admin;


import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.model.entity.Category;
import club.lazyzzz.web.model.vo.CategoryVO;
import club.lazyzzz.web.service.ICategoryService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@RestController
@RequestMapping("/api/admin/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    @ApiOperation("返回全部分类")
    public List<Category> getAll() {
        return categoryService.list();
    }

    @PostMapping
    @ApiOperation("创建新的分类")
    public Response save(@RequestParam String name) {
        Category category = new Category();
        category.setName(name);
        categoryService.save(category);
        return Response.ok("分类创建成功", null);
    }

    @PutMapping
    @ApiOperation("修改分类")
    public Response update(@RequestParam Integer id,
                           @RequestParam String name) {
        categoryService.updateCategory(id, name);
        return Response.ok("分类修改成功", null);
    }

    @DeleteMapping
    @ApiOperation("删除分类")
    public Response delete(@RequestParam Integer id) {
        categoryService.removeCategory(id);
        return Response.ok("分类删除成功", null);
    }
}

