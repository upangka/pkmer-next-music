package io.gitee.pkmer.music.application.admin;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AdminLoginView {
    @Schema(description = "token")
    private String token;
    @Schema(description = "用户id")
    private String id;
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "头像")
    private String avatar;

}
