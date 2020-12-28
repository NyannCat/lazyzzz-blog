package club.lazyzzz.web.core;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

@Data
@ApiModel(description = "标准返回消息体")
public class Response {
    @ApiModelProperty("操作代码")
    private Integer code;

    @ApiModelProperty("消息")
    private String msg;

    @ApiModelProperty("数据")
    private Object data;

    public Response() {
    }

    public Response(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Response ok(@Nullable Object o) {
        return ok("", o);
    }

    public static Response ok(@NonNull String msg, @Nullable Object o) {
        return new Response(HttpStatus.OK.value(), msg, o);
    }
}
