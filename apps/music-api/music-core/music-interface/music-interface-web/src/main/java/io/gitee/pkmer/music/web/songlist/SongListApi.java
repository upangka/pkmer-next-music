package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.songlist.add.AddSongForListCmd;
import io.gitee.pkmer.music.application.songlist.query.SongListPageQuery;
import io.gitee.pkmer.music.application.songlist.query.SongListView;
import io.gitee.pkmer.music.application.songlist.update.UpdateSongListCmd;
import io.gitee.pkmer.music.web.songlist.req.AddSongListReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
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
    @PostMapping("/page")
    Result<PageResponse<SongListView>> pageQuery(@Valid @RequestBody SongListPageQuery pageQuery);

    @Operation(summary = "歌单基本信息")
    @PostMapping("/update/base")
    Result<Void> updateSongListBase(@Validated(UpdateSongListCmd.SongListBase.class)
                                    @RequestBody UpdateSongListCmd cmd);

    // todo 使用minio来尽心上传
    @Operation(summary = "歌单封面图片链接")
    @PostMapping("/update/pic")
    Result<Void> updateSongListPic(@Validated(UpdateSongListCmd.SongListPic.class)
                                   @RequestBody UpdateSongListCmd cmd);


    @Operation(summary = "添加歌曲到歌单")
    @PostMapping("/add/songs")
    Result<Void> addSongToSongList(@Validated
                                   @RequestBody AddSongForListCmd cmd);

}
