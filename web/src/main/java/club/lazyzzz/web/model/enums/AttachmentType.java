package club.lazyzzz.web.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

public enum AttachmentType {

    /**
     * 本地存储
     */
    LOCAL(0),

    /**
     * 七牛云
     */
    QI_NIU_YUN(1);

    AttachmentType(int code) {
        this.code = code;
    }

    @EnumValue
    @JsonValue
    private final int code;
}
