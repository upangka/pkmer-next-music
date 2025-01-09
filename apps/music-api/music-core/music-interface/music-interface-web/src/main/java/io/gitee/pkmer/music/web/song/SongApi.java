package io.gitee.pkmer.music.web.song;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.song.update.UpdateSongCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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


    @Operation(summary = "更新歌曲")
    @PostMapping("/update")
    Result<Void> updateSong(@Valid @RequestBody  UpdateSongCmd cmd);



}
