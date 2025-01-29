package io.gitee.pkmer.music.application.song.get;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class SongDetailView {
    @Schema(description = "歌曲id")
    private String id;

    @Schema(description = "歌手id")
    private String singerId;

    @Schema(description = "歌曲名称")
    private String name;

    @Schema(description = "歌曲简介")
    private String introduction;

    @Schema(description = "歌曲封面")
    private String pic;

    @Schema(description = "歌曲地址")
    private String url;

    @Schema(description = "歌词")
    private String lyric;
}
