package io.gitee.pkmer.music.web.user;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.user.SignUserCmd;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
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
@RequestMapping("/user")
public interface UserApi {


    @Operation(summary = "用户注册")
    @PostMapping("/signup")
    Result<Void> signUp(@Valid @RequestBody
                        SignUserCmd cmd);
}
