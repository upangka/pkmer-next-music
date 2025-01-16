package io.gitee.pkmer.music.web.user;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Tag(name = "用户")
@RequestMapping("/user")
public interface UserApi {


    @Operation(summary = "用户注册")
    @PostMapping("/signup")
    Result<Void> signUp(@Valid @RequestBody
                        SignUserCmd cmd);


    @Operation(summary = "用户登录")
    @PostMapping("/login")
    Result<LoginView> login(@Valid @RequestBody
                        LoginUserCmd cmd);


    @Operation(summary = "获取用户详情")
    @GetMapping("/detail")
    Result<UserDetailView> getUserDetail();
}
