package io.gitee.pkmer.music.web.collect;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectSongDto;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.collect.CancelCmd;
import io.gitee.pkmer.music.application.collect.CollectQuery;
import io.gitee.pkmer.music.application.collect.CollectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdminCollectController
        extends BaseController
        implements AdminCollectApi {


    private final CmdDispatcher cmdDispatcher;
    private final CollectService service;
    @Override
    public Result<Void> cancelCollectSong(Long userId, Long songId) {
        CancelCmd cmd = CancelCmd.commandOf(userId, songId, null);
        cmdDispatcher.dispatch(cmd);
        return success();
    }


    @Override
    public Result<PageResponse<CollectSongDto>> adminPageQuery(CollectQuery query) {
        return success(service.pageQueryWithSongName(query));
    }

    @Override
    public Result<TotalView> getPageTotal(CollectQuery query) {
        return success(service.getPageTotalWithSongName(query));
    }

}
