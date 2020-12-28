package club.lazyzzz.web.model.dto;

import org.springframework.beans.BeanUtils;

/**
 * @author Lazyzzz
 * @since  2020-12-17
 */
public interface InputConverter<DOMAIN> {
    /**
     * 将dto转为entity
     * @param domain entity实例
     * @return 拷贝后entity实例
     */
    default DOMAIN convertTo(DOMAIN domain) {
        BeanUtils.copyProperties(this, domain);
        return domain;
    }
}
