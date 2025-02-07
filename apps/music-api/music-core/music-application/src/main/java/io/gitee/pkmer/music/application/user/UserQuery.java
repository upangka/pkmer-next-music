package io.gitee.pkmer.music.application.user;

import io.gitee.pkmer.convention.page.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends PageQuery {
    @Schema(description = "用户名")
    private String username;
}
