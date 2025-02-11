package io.gitee.pkmer.music.web.song;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.song.get.SongDetailView;
import io.gitee.pkmer.music.application.song.query.SongQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "admin端歌曲相关接口")
@RequestMapping("/admin/song")
public interface AdminSongApi {

    @Operation(summary = "分页查询歌曲")
    @PostMapping("/page")
    Result<PageResponse<SongDetailView>> pageQuerySong(
            @Valid
            @RequestBody
            SongQuery query);


    @Operation(summary = "查询歌曲分页的总页数")
    @PostMapping("/page/total")
    Result<TotalView> getSongPageTotal(
            @Valid
            @RequestBody
            SongQuery query);
}
