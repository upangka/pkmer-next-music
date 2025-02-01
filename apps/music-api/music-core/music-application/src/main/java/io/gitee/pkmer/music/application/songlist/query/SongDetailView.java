package io.gitee.pkmer.music.application.songlist.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SongDetailView {

    @Schema(description = "歌单id")
    private String id;

    @Schema(description = "歌单标题")
    private String title;

    @Schema(description = "歌单封面")
    private String pic;

    @Schema(description = "歌单风格")
    private List<String> styles;

    @Schema(description = "歌单介绍")
    private String introduction;

    @Schema(description = "歌单包含的歌曲")
    List<SongView> songs;

    @Builder
    @Data
    public static class SongView{
        @Schema(description = "歌曲id")
        private String id;

        @Schema(description = "歌手id")
        private String singerId;

        @Schema(description = "歌手名称")
        private String singerName;


        @Schema(description = "歌曲名称")
        private String name;


        @Schema(description = "歌曲简介")
        private String introduction;


        @Schema(description = "歌曲封面")
        private String pic;


        @Schema(description = "歌曲链接")
        private String url;

    }
}
