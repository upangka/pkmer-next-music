package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.ddd.shared.command.Command;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class UpdateUserCmd implements Command {

    private Long userId;

    @Schema(description = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "性别")
    @NotNull(message = "性别不能为空")
    private Byte sex;


    @Schema(description = "手机号")
    @NotBlank(message = "手机号不能为空")
    private String phoneNum;


    @Schema(description = "邮箱")
    @Email(message = "邮箱格式不正确")
    private String email;


    @Schema(description = "生日")
    @NotNull(message = "生日不能为空")
    private LocalDateTime birth;


    @Schema(description = "简介")
    @NotBlank(message = "简介不能为空")
    private String introduction;


    @Schema(description = "地址")
    @NotBlank(message = "地址不能为空")
    private String location;

}
