package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.Consumer;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicMapper;
import io.gitee.pkmer.core.infrastructure.persistence.consumer.mybatis.ConsumerDynamicSqlSupport;
import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.user.UserGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@Slf4j
@Component
public class LoginUserHandler implements CommandHandler<LoginUserCmd,LoginView> {

    private final UserGateway userGateway;
    private final ConsumerDynamicMapper userMapper;

    public LoginUserHandler(UserGateway userGateway, ConsumerDynamicMapper consumerDynamicMapper){
        this.userGateway = userGateway;
        this.userMapper = consumerDynamicMapper;
    }

    /**
     * 执行用户登录操作
     *
     * 本方法通过接收用户登录命令对象，验证用户身份信息，包括用户是否存在以及密码是否正确
     * 如果验证通过，则生成登录令牌（Token），并返回给客户端
     *
     * @param loginUserCmd 用户登录命令对象，包含用户输入的邮箱和密码
     * @return 登录成功后返回登录视图对象，包含生成的Token
     * @throws RuntimeException 如果用户不存在或密码错误，抛出运行时异常
     */

    @Override
    public LoginView execute(LoginUserCmd loginUserCmd) {
        Consumer user = userMapper.selectOne(c ->
                        c.where(ConsumerDynamicSqlSupport.email, isEqualTo(loginUserCmd.getEmail())))
                .orElseThrow(() -> new RuntimeException("用户不存在"));

        if(userGateway.checkPassword(loginUserCmd.getPassword(), user.getPassword())){
            String token = userGateway.generateJWT(user.getId(), user.getEmail(),user.getUsername());
            log.info("{} 登录的token {}",user.getUsername(),token);

            return new LoginView().setToken(token);
        }else{
            throw new RuntimeException("密码错误");
        }
    }
}
