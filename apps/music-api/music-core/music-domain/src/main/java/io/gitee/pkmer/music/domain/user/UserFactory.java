package io.gitee.pkmer.music.domain.user;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

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
        //userValidator.validate(email, rawPassword);  // 使用注入的验证器进行验证

        String encryptedPassword = userGateway.encodePassword(rawPassword);

        UserAggregate user = UserAggregate.builder()
                .username(generateRandomUsername()) // 默认用户名与邮箱相同
                .email(email)
                .password(encryptedPassword)
                .build();
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        return user;
    }

    /**
     * 生成随机用户名
     * @return  生成的随机用户名，格式为"user_加上8位UUID
     */
    private String generateRandomUsername() {
        // 使用UUID生成一个唯一的随机用户名
        return "user_" + UUID.randomUUID().toString().substring(0, 8); // 取UUID的前8个字符
    }
}
