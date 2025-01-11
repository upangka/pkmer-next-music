package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserFactory;
import io.gitee.pkmer.music.domain.user.UserRepository;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

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
    public static final String msgTemplate = """
                    用户注册失败 {0} 邮箱重复
                    """;
    public SignUserHandler(UserRepository userRepository,
                           UserFactory userFactory){

        this.userRepository = userRepository;
        this.userFactory = userFactory;
    }

    @Override
    public Void execute(SignUserCmd signUserCmd) {
        UserAggregate userAggregate = userFactory.register(signUserCmd.getEmail(),
                signUserCmd.getPassword());
        try{
            userRepository.save(userAggregate);
            return null;
        }catch (Exception e){
            throw new RuntimeException(createErrorMsg(signUserCmd.getEmail()));
        }
    }


    public static String createErrorMsg(String email){
        return MessageFormat.format(msgTemplate, email);
    }
}
