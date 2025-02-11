package io.gitee.pkmer.music.web.song;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.song.SongService;
import io.gitee.pkmer.music.application.song.get.SongDetailView;
import io.gitee.pkmer.music.application.song.query.SongQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminSongController extends BaseController
    implements  AdminSongApi
{

    private final SongService songService;

    @Override
    public Result<PageResponse<SongDetailView>> pageQuerySong(SongQuery query) {
        return success(songService.pageQuerySongDetail(query));
    }

    @Override
    public Result<TotalView> getSongPageTotal(SongQuery query) {
        return success(songService.getPageQueryTotal(query));
    }
}
