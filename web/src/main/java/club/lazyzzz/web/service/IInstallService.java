package club.lazyzzz.web.service;

import club.lazyzzz.web.model.dto.UserRegisterDto;

public interface IInstallService {
    /**
     * 注册博客
     * @param dto 用户注册对象
     */
    void install(UserRegisterDto dto);
}
