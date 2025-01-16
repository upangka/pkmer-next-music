package io.gitee.pkmer.music.web.user;
import io.gitee.pkmer.music.application.user.*;
import io.gitee.pkmer.security.context.AppContextHolder;
import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.security.jwt.bo.JWTUserBo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class UserController extends BaseController implements UserApi {

    @Setter(onMethod_ = @Autowired)
    private CmdDispatcher cmdDispatcher;

    @Override
    public Result<Void> signUp(SignUserCmd cmd) {
         cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<LoginView> login(LoginUserCmd cmd) {
        return success(cmdDispatcher.dispatch(cmd));
    }

    @Override
    public Result<UserDetailView> getUserDetail() {

        JWTUserBo user = AppContextHolder.userContextHolder.getUser();
        GetUserDetailCmd userDetailCmd = new GetUserDetailCmd();
        userDetailCmd.setUserId(user.getId());
        return success(cmdDispatcher.dispatch(userDetailCmd));
    }
}
