package io.gitee.pkmer.music.web.user;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "更新用户资料")
    @PostMapping("/detail")
    Result<Void> updateUserDetail(@Valid @RequestBody
                                            UpdateUserCmd cmd);

    @Operation(summary = "分页查询用户")
    @PostMapping("/page")
    Result<PageResponse<UserDetailView>> pageQueryUser(@RequestBody
                                                        UserQuery query);

    @Operation(summary = "获取用户分页总数")
    @PostMapping("/page/total")
    Result<TotalView> getTotal(@RequestBody
                                        UserQuery query);

    @Operation(summary = "删除用户")
    @Parameters({
            @Parameter(name = "id", description = "用户id", required = true)
    })
    @DeleteMapping("/{id}")
    Result<Void> deleteUser(@PathVariable("id") Long id);
}
