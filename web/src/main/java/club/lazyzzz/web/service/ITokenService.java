package club.lazyzzz.web.service;

import com.auth0.jwt.interfaces.DecodedJWT;

import javax.annotation.Nonnull;

public interface ITokenService {

    /**
     * 生成token
     * @param userId 用户id
     * @return token字符串
     */
    String generator(@Nonnull Integer userId);

    /**
     * 验证token
     * @param token token字符串
     * @return 解析后的token
     */
    DecodedJWT verify(@Nonnull String token);
}
