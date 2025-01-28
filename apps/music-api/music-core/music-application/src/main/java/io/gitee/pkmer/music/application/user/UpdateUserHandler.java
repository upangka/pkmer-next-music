package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.singer.Sex;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserId;
import io.gitee.pkmer.music.domain.user.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserHandler implements CommandHandler<UpdateUserCmd,Void> {

    private final UserRepository userRepository;
    public UpdateUserHandler(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Void execute(UpdateUserCmd cmd) {
        UserAggregate user = userRepository.load(new UserId(cmd.getUserId())).orElseThrow(
                () -> new RuntimeException("用户不存在")
        );

        user.changeUsername(cmd.getUsername())
                .changeLocation(cmd.getLocation())
                .changeIntroduction(cmd.getIntroduction())
                .changeBirth(cmd.getBirth())
                .changeEmail(cmd.getEmail())
                .changePhoneNum(cmd.getPhoneNum())
                .changeSex(Sex.valueOf(cmd.getSex()))
                .toUpdate();

        userRepository.save(user);
        return null;
    }

}
