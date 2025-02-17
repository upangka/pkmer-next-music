package io.gitee.pkmer.music.application.admin;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * admin用户登录的命令
 */
@Data
public class AdminLoginCmd implements Command {
    @Schema(description = "用户名")
    @NotBlank
    private String username;
    @Schema(description = "密码")
    @NotBlank
    @Size(min = 6, message = "密码长度不能小于6位")
    private String password;
}
