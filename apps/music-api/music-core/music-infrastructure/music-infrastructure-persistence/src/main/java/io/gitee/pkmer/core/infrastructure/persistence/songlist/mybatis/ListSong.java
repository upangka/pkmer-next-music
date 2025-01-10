package io.gitee.pkmer.core.infrastructure.persistence.songlist.mybatis;

import io.gitee.pkmer.mybatis.annotations.SnowflakeId;
import lombok.Data;

/**
 * generated by MyBatis Generator.
 * list_song(歌单歌曲关联表) 
 * 
 * @author 胖卡
 * @version 1.0.0
 * @date 2025/01/10
 */
@Data
public class ListSong {
   @SnowflakeId
    private Long id;


    private Long songId;


    private Long songListId;

}