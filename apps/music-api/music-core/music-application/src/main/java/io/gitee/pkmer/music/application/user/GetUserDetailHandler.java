package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.minio.props.PkmerMinioProps;
import io.gitee.pkmer.music.domain.user.UserAggregate;
import io.gitee.pkmer.music.domain.user.UserId;
import io.gitee.pkmer.music.domain.user.UserRepository;
import io.minio.MinioProperties;
import org.springframework.stereotype.Component;

@Component
public class GetUserDetailHandler implements CommandHandler<GetUserDetailCmd,UserDetailView> {

    private final UserRepository repository;
    private final String minioServer;
    public GetUserDetailHandler(UserRepository userRepository,
                                PkmerMinioProps props){
        this.repository = userRepository;
        this.minioServer = props.getUrl();
    }
    @Override
    public UserDetailView execute(GetUserDetailCmd cmd) {
        UserAggregate user = repository.load(new UserId(cmd.getUserId())).orElseThrow();
        return toView(user);
    }

    private UserDetailView toView(UserAggregate user){
        // 将用户聚合根转换为视图UserAggregate转化为UserDetailView，其中UserDetailView为huilder模式
        return UserDetailView.builder()
                .id(user.getId().value().toString())
                .username(user.getUsername())
                .sex(user.getSex().getValue())
                .phoneNum(user.getPhoneNum())
                .email(user.getEmail())
                .birth(user.getBirth())
                .introduction(user.getIntroduction())
                .location(user.getLocation())
                .avator(minioServer + user.getAvator())
                .build();
    }
}
