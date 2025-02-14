package io.gitee.pkmer.music.web.song;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.song.get.SongDetailView;
import io.gitee.pkmer.music.application.song.update.UpdateSongCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/9
 * </p>
 */
@Tag(name = "歌曲")
@RequestMapping("/song")
public interface SongApi {

    @Parameters({
            @Parameter(name = "id",description = "歌曲id",in = ParameterIn.PATH)
    })
    @Operation(summary = "删除歌曲")
    @DeleteMapping("/{id}")
    Result<Void> deleteSong(@PathVariable("id") Long id);


    /**
     * 更新歌曲，这里歌曲文件，直接走minio大文件分片上传。
     * @param cmd
     * @param lyricFile
     * @return
     */
    @Operation(summary = "更新歌曲")
    @PostMapping("/update")
    Result<Void> updateSong(@ModelAttribute UpdateSongCmd cmd,
                            @RequestParam("lyricFile")MultipartFile lyricFile);

    @Operation(summary = "收藏歌曲")
    @Parameters({
            @Parameter(name = "id",description = "歌曲id",in = ParameterIn.PATH),
    })
    @PostMapping("/collect/{id}")
    Result<Void> collectSongList(@PathVariable Long songId);

    @Operation(summary = "获取歌曲详情")
    @Parameters({
            @Parameter(name = "id",description = "歌曲id",in = ParameterIn.PATH),
    })
    @GetMapping("/{id}")
    Result<SongDetailView> getSongDetail(@PathVariable("id") Long id);
}
