package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.songlist.get.SongListDto;
import io.gitee.pkmer.music.application.songlist.get.SongListPageQueryCmd;
import io.gitee.pkmer.music.web.songlist.req.AddSongListReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 歌单接口
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Tag(name = "歌单")
@RequestMapping("songList")
public interface SongListApi {

    /**
     * 删除歌单
     */
    @Operation(summary = "删除歌单")
    @Parameters({
            @Parameter(name = "id",description = "歌单id",in = ParameterIn.PATH),
    })
    @DeleteMapping("/{id}")
    Result<Void> delete(@PathVariable("id") String id);

    /**
     * 添加歌单
     */
    @Operation(summary = "添加歌单")
    @PostMapping
    Result<Void> addSongList(@Valid @RequestBody AddSongListReq songList);


    /**
     * 分页查询歌单
     */
    @Operation(summary = "歌单分页查询")
    @GetMapping("/page")
    Result<PageResponse<SongListDto>> pageQuery(@Valid @RequestBody SongListPageQueryCmd cmd);



}
