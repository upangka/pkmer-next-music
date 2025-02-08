package io.gitee.pkmer.music.application.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalView {
    @Schema(description = "总记录数")
    private int total;
    @Schema(description = "总页数")
    private int totalPages;
}
