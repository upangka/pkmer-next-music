package io.gitee.pkmer.music.application.admin;

import io.gitee.pkmer.core.infrastructure.persistence.admin.mybatis.AdminMapper;
import io.gitee.pkmer.core.infrastructure.persistence.admin.mybatis.ManagementAdmin;
import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.user.UserGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import static  io.gitee.pkmer.core.infrastructure.persistence.admin.mybatis.ManagementAdminDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

@RequiredArgsConstructor
@Component
@Slf4j
public class AdminLoginHandler implements CommandHandler<AdminLoginCmd,AdminLoginView> {

    private final UserGateway userGateway;
    private final AdminMapper adminMapper;

    @Override
    public AdminLoginView execute(AdminLoginCmd cmd) {

        ManagementAdmin adminUser = adminMapper.selectOne(c ->
                c.where(name, isEqualTo(cmd.getUsername()))
        ).orElseThrow(() -> new RuntimeException("用户名或密码错误"));


        if(userGateway.checkPassword(cmd.getPassword(), adminUser.getPassword())){
            // admin登录没有提供email，这里用空字符串代替，不能使用null
            String token = userGateway.generateJWT(adminUser.getId(), "",adminUser.getName());
            log.info("{} 登录的token {}",adminUser.getName(),token);

            return new AdminLoginView()
                    .setId(adminUser.getId().toString())
                    .setUsername(adminUser.getName())
                    .setToken(token);
        }else{
            throw new RuntimeException("密码错误");
        }
    }
}
