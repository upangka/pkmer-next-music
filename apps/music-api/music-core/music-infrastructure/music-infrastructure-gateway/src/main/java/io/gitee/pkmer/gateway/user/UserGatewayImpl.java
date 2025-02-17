package io.gitee.pkmer.gateway.user;

import io.gitee.pkmer.music.domain.user.UserGateway;
import io.gitee.pkmer.security.jwt.bo.JWTUserBo;
import io.gitee.pkmer.security.jwt.service.JWTService;
import io.gitee.pkmer.security.password.AppPasswordEncoder;
import org.springframework.stereotype.Repository;

/**
 * user网关防腐层，用于统一处理用户的token
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
    private final JWTService jwtService;
    public UserGatewayImpl(AppPasswordEncoder appPasswordEncoder,
                           JWTService jwtService) {
        this.passwordEncoder = appPasswordEncoder;
        this.jwtService = jwtService;
    }
    @Override
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public String generateJWT(Long userId, String email,String username) {
        JWTUserBo jwtUserBo = new JWTUserBo();
        jwtUserBo.setId(userId);
        jwtUserBo.setUsername(username);
        jwtUserBo.setEmail(email);
        return jwtService.generateToken(jwtUserBo);
    }

    @Override
    public boolean checkPassword(String password, String encodedPassword) {
        try {
            return passwordEncoder.check(password, encodedPassword);
        } catch (Exception e) {
            return false;
        }
    }
}
