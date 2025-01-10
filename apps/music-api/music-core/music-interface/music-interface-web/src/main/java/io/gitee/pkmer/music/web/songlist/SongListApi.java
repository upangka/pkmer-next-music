package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.songlist.add.AddCommentCmd;
import io.gitee.pkmer.music.application.songlist.add.AddSongForListCmd;
import io.gitee.pkmer.music.application.songlist.delete.DeleteCommentCmd;
import io.gitee.pkmer.music.application.songlist.delete.DeleteSongForListCmd;
import io.gitee.pkmer.music.application.songlist.query.SongListCommentQuery;
import io.gitee.pkmer.music.application.songlist.query.SongListCommentsView;
import io.gitee.pkmer.music.application.songlist.query.SongListPageQuery;
import io.gitee.pkmer.music.application.songlist.query.SongListView;
import io.gitee.pkmer.music.application.songlist.update.UpdateSongListCmd;
import io.gitee.pkmer.music.application.songlist.update.UpdateSongListCommentCmd;
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


    @Operation(summary = "删除歌单")
    @Parameters({
            @Parameter(name = "id",description = "歌单id",in = ParameterIn.PATH),
    })
    @DeleteMapping("/{id}")
    Result<Void> delete(@PathVariable("id") String id);

    @Operation(summary = "添加歌单")
    @PostMapping
    Result<Void> addSongList(@Valid @RequestBody AddSongListReq songList);


    @Operation(summary = "歌单分页查询")
    @PostMapping("/page")
    Result<PageResponse<SongListView>> pageQuerySongList(@Valid @RequestBody SongListPageQuery pageQuery);

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


    @Operation(summary = "添加歌曲到歌单")
    @DeleteMapping("/delete/songs")
    Result<Void> deleteSongToSongList(@Validated
                                   @RequestBody DeleteSongForListCmd cmd);


    @Operation(summary = "更新歌单的评论")
    @PostMapping("/update/comments")
    Result<Void> updateSongListComment(@Valid @RequestBody
                                       UpdateSongListCommentCmd cmd);


    @Operation(summary = "删除歌单的评论")
    @PostMapping("/delete/comments")
    Result<Void> deleteSongListComment(@Valid @RequestBody
                                       DeleteCommentCmd cmd);

    @Operation(summary = "新增歌单的评论")
    @PostMapping("/add/comments")
    Result<Void> addSongListComment(@Valid @RequestBody
                                    AddCommentCmd cmd);


    @Operation(summary = "歌单评论分页查询")
    @PostMapping("/comments/page")
    Result<PageResponse<SongListCommentsView>> pageQueryComments(@Valid @RequestBody
                                                         SongListCommentQuery pageQuery);

    @Operation(summary = "收藏歌单")
    @Parameters({
            @Parameter(name = "id",description = "歌单id",in = ParameterIn.PATH),
    })
    @PostMapping("/collect/{id}")
    Result<Void> collectSongList(@PathVariable Long songListId);
}
