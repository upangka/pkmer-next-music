package io.gitee.pkmer.music.web.collect;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.collect.CollectQuery;
import io.gitee.pkmer.music.application.collect.CollectView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "收藏")
@RequestMapping("/collect")
public interface CollectApi {


    @Operation(summary = "分页查询收藏")
    @GetMapping
    Result<PageResponse<CollectView>> pageQuery(CollectQuery query);

    @Operation(summary = "查询是否收藏歌曲")
    @Parameters({
            @Parameter(name = "songId", description = "歌曲id", in = ParameterIn.PATH)
    })
    @GetMapping("/isCollectSong/{songId}")
    Result<Boolean> isCollectSong(@PathVariable Long songId);


    @Operation(summary = "取消收藏歌曲")
    @Parameters({
            @Parameter(name = "id", description = "歌曲id", in = ParameterIn.PATH)
    })
    @DeleteMapping("/song/{id}")
    Result<Void> cancelCollectSong(@PathVariable Long id);


    @Operation(summary = "取消收藏歌单")
    @Parameters({
            @Parameter(name = "id", description = "歌单id", in = ParameterIn.PATH)
    })
    @DeleteMapping("/songList/{id}")
    Result<Void> cancelCollectSongList(@PathVariable Long id);


    @Operation(summary = "收藏歌单")
    @Parameters({
            @Parameter(name = "id", description = "歌单id", in = ParameterIn.PATH)
    })
    @PostMapping("/songList/{id}")
    Result<Void> collectSongList(@PathVariable Long id);

    @Operation(summary = "收藏歌曲")
    @Parameters({
            @Parameter(name = "id", description = "歌曲id", in = ParameterIn.PATH)
    })
    @PostMapping("/song/{id}")
    Result<Void> collectSong(@PathVariable Long id);

}
