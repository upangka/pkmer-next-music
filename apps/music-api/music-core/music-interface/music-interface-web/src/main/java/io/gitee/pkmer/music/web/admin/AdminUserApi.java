package io.gitee.pkmer.music.web.admin;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.admin.AdminLoginCmd;
import io.gitee.pkmer.music.application.admin.AdminLoginView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "管理用户端")
@RequestMapping("/admin/user")
public interface AdminUserApi {

    @Operation(summary = "用户登录")
    @PostMapping("/login")
    Result<AdminLoginView> login(@Valid @RequestBody
                                 AdminLoginCmd cmd);
}
