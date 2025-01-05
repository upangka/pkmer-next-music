package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.result.Result;
import org.springframework.web.bind.annotation.*;

/**
 * 歌单接口
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@RequestMapping("songList")
public interface SongListApi {

    /**
     * 删除歌单
     */
    @GetMapping("/{id}")
    Result<Void> delete(@PathVariable("id") String id);

    /**
     * 添加歌单
     */
    @PostMapping
    Result<Void> addSongList(@RequestBody SongListReq songList);




}
