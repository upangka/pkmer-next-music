package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserCmd implements Command {
    @Schema(description = "邮箱")
    @Email(message= "邮箱格式不正确")
    private String email;
    @Schema(description = "密码")
    @NotBlank(message= "密码不能为空")
    private String password;
}
