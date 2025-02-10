package io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

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
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CollectSongDto {

    private Long id;
    private Long userId;
    private String type;
    private SongDto song;

    private Long songId;


    private LocalDateTime createTime;

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class SongDto{
        private Long id;
        private String name;

        private String singerId;

        private String singerName;

        private String url;

        private String pic;

        private String introduction;

    }
}

