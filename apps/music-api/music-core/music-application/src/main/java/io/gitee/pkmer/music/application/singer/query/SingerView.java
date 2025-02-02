package io.gitee.pkmer.music.application.singer.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingerView {

    @Schema(description = "歌手id")
    private String id;


    @Schema(description = "歌手名称")
    private String name;


    @Schema(description = "歌手性别",example = "MALE")
    private String sex;


    @Schema(description = "歌手图片")
    private String pic;


    @Schema(description = "歌手生日")
    private LocalDateTime birth;


    @Schema(description = "歌手地区")
    private String location;


    @Schema(description = "歌手介绍")
    private String introduction;

    @Schema(description = "歌手歌曲")
    List<SongView> songs;


    @Builder
    @Data
    public static class SongView{
        @Schema(description = "歌曲id")
        private String id;

        @Schema(description = "歌曲名称")
        private String name;

        @Schema(description = "歌曲简介")
        private String introduction;

        @Schema(description = "歌曲封面")
        private String picture;

        @Schema(description = "歌曲地址")
        private String link;
    }


}
