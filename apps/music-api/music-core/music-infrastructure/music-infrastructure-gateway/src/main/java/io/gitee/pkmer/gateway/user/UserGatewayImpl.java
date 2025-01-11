package io.gitee.pkmer.gateway.user;

import io.gitee.pkmer.music.domain.user.UserGateway;
import io.gitee.pkmer.security.password.AppPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/11
 * </p>
 */
@Repository
public class UserGatewayImpl implements UserGateway {
    private final AppPasswordEncoder passwordEncoder;

    public UserGatewayImpl(AppPasswordEncoder appPasswordEncoder) {
        this.passwordEncoder = appPasswordEncoder;
    }
    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
