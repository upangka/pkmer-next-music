package io.gitee.pkmer.music.application.song.dto;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
public class SongDto {

    /**
     * 歌曲id
     */
    private Long id;

    /**
     * 歌手id
     */
    private Long singerId;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 歌曲名称
     */
    private String name;


    /**
     * 歌曲专辑
     */
    private String introduction;


    /**
     * 歌曲封面
     */
    private String pic;

    /**
     * 歌曲链接
     */
    private String url;


}
