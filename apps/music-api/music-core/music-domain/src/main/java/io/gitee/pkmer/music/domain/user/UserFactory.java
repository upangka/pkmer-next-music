package io.gitee.pkmer.music.domain.user;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 用户领域工厂
 * <p>
 * 负责用户的创建与验证
 * </p>
 */
@Component
public class UserFactory {

    private final UserGateway userGateway;
    private final UserValidator userValidator;

    public UserFactory(UserGateway userGateway, UserValidator userValidator) {
        this.userGateway = userGateway;
        this.userValidator = userValidator;
    }

    /**
     * 注册新用户
     *
     * @param email 用户的邮箱地址，作为用户的唯一标识
     * @param rawPassword 用户的原始密码，未加密
     * @return 返回新创建的用户对象
     */
    public UserAggregate register(String email, String rawPassword) {
        userValidator.validate(email, rawPassword);  // 使用注入的验证器进行验证

        String encryptedPassword = userGateway.encodePassword(rawPassword);

        UserAggregate user = UserAggregate.builder()
                .username(email) // 默认用户名与邮箱相同
                .email(email)
                .password(encryptedPassword)
                .build();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }
}
