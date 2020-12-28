package club.lazyzzz.web.service.impl;

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

import javax.annotation.Nonnull;
import java.security.SecureRandom;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TokenServiceImpl implements ITokenService {

    private static final long EXPIRE_TIME = TimeUnit.DAYS.toMillis(1);

    private final Algorithm algorithm;
    private final JWTCreator.Builder builder;
    private final JWTVerifier verifier;

    public TokenServiceImpl() {
        byte[] secret = new byte[256];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(secret);
        this.algorithm = Algorithm.HMAC256(secret);
        this.builder = JWT.create().withIssuer("lazyzzz");
        this.verifier = JWT.require(algorithm).withIssuer("lazyzzz").build();
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
