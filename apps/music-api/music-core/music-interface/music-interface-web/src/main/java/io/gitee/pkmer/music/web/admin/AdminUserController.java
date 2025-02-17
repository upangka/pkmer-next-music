package io.gitee.pkmer.music.web.admin;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.admin.AdminLoginCmd;
import io.gitee.pkmer.music.application.admin.AdminLoginView;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminUserController extends BaseController implements AdminUserApi {
   private final CmdDispatcher cmdDispatcher;

    @Override
    public Result<AdminLoginView> login(AdminLoginCmd cmd) {
        return success(cmdDispatcher.dispatch(cmd));
    }
}
