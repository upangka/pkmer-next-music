package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

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
@Data
public class SignUserCmd implements Command {
    @Schema(description = "邮箱")
    @Email(message= "邮箱格式不正确")
    private String email;
    @Schema(description = "密码")
    @NotBlank(message= "密码不能为空")
    private String password;
}
