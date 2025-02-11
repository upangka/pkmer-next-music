package io.gitee.pkmer.music.web.collect;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectSongDto;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.collect.*;
import io.gitee.pkmer.security.context.AppContextHolder;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;

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
@RestController
public class CollectController extends BaseController implements CollectApi {

    private final CollectService service;
    private final CmdDispatcher cmdDispatcher;

    public CollectController(CollectService service, CmdDispatcher cmdDispatcher) {
        this.service = service;
        this.cmdDispatcher = cmdDispatcher;
    }

    @Override
    public Result<PageResponse<CollectView>> userPageQuery(CollectQuery query) {
        query.setUserId(getCurrentUserId());
        return success(service.pageQuery(query));
    }

    @Override
    public Result<TotalView> getPageTotal(CollectQuery query) {
        return success(service.getPageTotal(query));
    }

    @Override
    public Result<Boolean> isCollectSong(Long songId) {
        boolean collectSong = service.isCollectSong(getCurrentUserId(), songId);
        return success(collectSong);
    }

    @Override
    public Result<Void> cancelCollectSong(Long id) {
        CancelCmd cmd = CancelCmd.commandOf(
                getCurrentUserId(),
                id,
                null
        );
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> cancelCollectSongList(Long id) {
        CancelCmd cmd = CancelCmd.commandOf(
                getCurrentUserId(),
                null,
                id
        );
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> collectSongList(Long id) {
        CollectCmd cmd = new CollectCmd().setUserId(getCurrentUserId())
                .setSongListId(id);
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> collectSong(Long id) {
        CollectCmd cmd = new CollectCmd().setUserId(getCurrentUserId())
                .setSongId(id);
        cmdDispatcher.dispatch(cmd);
        return success();
    }



    private Long getCurrentUserId(){
        return AppContextHolder.userContextHolder.getUser().getId();
    }
}
