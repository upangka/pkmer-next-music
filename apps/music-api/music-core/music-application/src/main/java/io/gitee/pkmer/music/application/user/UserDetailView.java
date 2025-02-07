package io.gitee.pkmer.music.application.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
@Builder
@Data
public class UserDetailView {

    @Schema(description = "用户ID", example = "12345")
    private String id;

    @Schema(description = "用户名", example = "john_doe")
    private String username;

    @Schema(description = "性别", example = "男")
    private Byte sex;

    @Schema(description = "手机号码", example = "13800138000")
    private String phoneNum;

    @Schema(description = "电子邮件", example = "john.doe@example.com")
    private String email;

    @Schema(description = "出生日期", example = "2000-01-01T00:00:00")
    private LocalDateTime birth;

    @Schema(description = "个人简介", example = "热爱音乐的开发者")
    private String introduction;

    @Schema(description = "所在位置", example = "北京市")
    private String location;

    @Schema(description = "头像URL", example = "http://example.com/avatar.jpg")
    private String avator;

    @Schema(description = "创建时间", example = "2023-10-01T12:34:56")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", example = "2023-10-01T12:34:56")
    private LocalDateTime updateTime;
}