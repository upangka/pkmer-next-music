package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserRepository;
import io.gitee.pkmer.security.password.AppPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

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
@Component
public class SignUserHandler implements CommandHandler<SignUserCmd, Void> {

    private final UserRepository userRepository;
    private final AppPasswordEncoder appPasswordEncoder;

    public SignUserHandler(UserRepository userRepository,
                           AppPasswordEncoder appPasswordEncoder){

        this.userRepository = userRepository;
        this.appPasswordEncoder = appPasswordEncoder;
    }

    @Override
    public Void execute(SignUserCmd signUserCmd) {
        String encodePwd = appPasswordEncoder.encode(signUserCmd.getPassword());
        UserAggregate userAggregate = UserAggregate.builder()
                .username(signUserCmd.getEmail()) // 用户名 默认邮箱名
                .email(signUserCmd.getEmail())
                .password(encodePwd)
                .build();

        userAggregate.setCreateTime(LocalDateTime.now());
        userAggregate.setUpdateTime(LocalDateTime.now());
        userRepository.save(userAggregate);

        return null;
    }
}
