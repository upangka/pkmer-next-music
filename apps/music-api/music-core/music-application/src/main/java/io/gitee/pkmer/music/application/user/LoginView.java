package io.gitee.pkmer.music.application.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class LoginView {
    @Schema(description = "token")
    private String token;
}
