package club.lazyzzz.web.controller.api.admin;

import club.lazyzzz.web.core.Response;
import club.lazyzzz.web.mapper.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

@RestController
@RequestMapping("/api/admin/statistics")
public class StatisticsController {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private SysLogMapper sysLogMapper;

    @GetMapping("/logs")
    public Response logs(@RequestParam Integer page, @RequestParam Integer size) {
        return Response.ok(sysLogMapper.selectPage(new Page<>(page, size)));
    }

    @GetMapping("/dashboard")
    public Response dashboard() {
        HashMap<String, Object> map = getBasicInfo();
        map.put("comments", commentMapper.selectPage(new Page<>(1, 10), null));
        map.put("totalViews", articleMapper.selectTotalViews());
        return Response.ok(map);
    }

    @GetMapping("/user")
    public Response user() {
        HashMap<String, Object> map = getBasicInfo();
        map.put("categoryCount", categoryMapper.selectCount(null));
        map.put("user", userMapper.selectById(1));
        return Response.ok(map);
    }

    private HashMap<String, Object> getBasicInfo() {
        HashMap<String, Object> map = new HashMap<>(8);
        map.put("articleCount", articleMapper.selectCount(null));
        map.put("commentCount", commentMapper.selectCount(null));
        map.put("duration", Duration.between(userMapper.selectCreateTimeById(1), LocalDateTime.now()).toDays());
        return map;
    }
}
