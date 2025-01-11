package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserFactory;
import io.gitee.pkmer.music.domain.user.UserRepository;
import org.springframework.stereotype.Component;

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
    private final UserFactory userFactory;

    public SignUserHandler(UserRepository userRepository,
                           UserFactory userFactory){

        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public Void execute(SignUserCmd signUserCmd) {
        UserAggregate userAggregate = userFactory.register(signUserCmd.getEmail(),
                signUserCmd.getPassword());
        userRepository.save(userAggregate);
        return null;
    }
}
