package io.gitee.pkmer.music.application.song.query;

import io.gitee.pkmer.convention.page.query.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SongQuery extends PageQuery {
    @Schema(description = "歌手id",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull
    private final Long singerId;
    @Schema(description = "歌曲名称")
    private final String name;
}
