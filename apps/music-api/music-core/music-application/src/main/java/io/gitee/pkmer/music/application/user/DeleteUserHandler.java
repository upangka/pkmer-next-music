package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserId;
import io.gitee.pkmer.music.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteUserHandler implements CommandHandler<DeleteUserCmd,Void> {
   private final UserRepository userRepository;

    @Override
    public Void execute(DeleteUserCmd cmd) {
        UserAggregate user = userRepository.load(new UserId(cmd.getId())).orElseThrow(
                () -> new RuntimeException("用户不存在")
        );
        user.toDelete();
        userRepository.save(user);
        return null;
    }
}
