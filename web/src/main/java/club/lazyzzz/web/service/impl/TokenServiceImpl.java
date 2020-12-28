package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.config.property.LazyzzzProperty;
import club.lazyzzz.web.exception.SecurityException;
import club.lazyzzz.web.service.ITokenService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TokenServiceImpl implements ITokenService {

    private static final long EXPIRE_TIME = TimeUnit.DAYS.toMillis(1);

    private final Algorithm algorithm;
    private final JWTCreator.Builder builder;
    private final JWTVerifier verifier;

    public TokenServiceImpl(LazyzzzProperty lazyzzzProperty) {
        String secret = lazyzzzProperty.getTokenSecret();
        Assert.hasText(secret, "token密钥不能为空");
        String issuer = "lazyzzz";
        this.algorithm = Algorithm.HMAC256(secret);
        this.builder = JWT.create().withIssuer(issuer);
        this.verifier = JWT.require(algorithm).withIssuer(issuer).build();
    }

    @Override
    public String generator(@Nonnull Integer userId) {
        return builder.withClaim("userId", userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .sign(algorithm);
    }

    @Override
    public DecodedJWT verify(@Nonnull String token) {
        try {
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            log.warn("token:{} 验证失败", token);
            throw new SecurityException("token不存在或已过期").setErrorData(token);
        }
    }
}
