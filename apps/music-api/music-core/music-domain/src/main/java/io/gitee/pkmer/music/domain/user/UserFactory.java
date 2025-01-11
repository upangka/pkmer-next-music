package io.gitee.pkmer.music.domain.user;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用户领域工厂
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/11
 * </p>
 */
@Component
public class UserFactory {

    private final UserGateway userGateway;

    public UserFactory(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public UserAggregate register(String email, String rawPassword) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }
        if (rawPassword == null || rawPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        String encryptedPassword = userGateway.encodePassword(rawPassword);

        UserAggregate userAggregate = UserAggregate.builder()
                .username(email) // 默认用户名与邮箱相同
                .email(email)
                .password(encryptedPassword)
                .build();

        userAggregate.setCreateTime(LocalDateTime.now());
        userAggregate.setUpdateTime(LocalDateTime.now());
        return userAggregate;
    }
}
