package io.gitee.pkmer.music.web.singer;

import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.singer.add.AddSingerCmd;
import io.gitee.pkmer.music.application.singer.delete.DeleteSingerCmd;
import io.gitee.pkmer.music.application.singer.query.GetSingerDetailCmd;
import io.gitee.pkmer.music.application.singer.query.SingerQuery;
import io.gitee.pkmer.music.application.singer.query.SingerService;
import io.gitee.pkmer.music.application.singer.query.SingerView;
import io.gitee.pkmer.music.application.singer.update.UpdateSingerCmd;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
public class SingerController extends BaseController implements SingerApi {

    @Setter(onMethod_ = @Autowired)
    private CmdDispatcher cmdDispatcher;

    @Setter(onMethod_ = @Autowired)
    private SingerService singerService;


    @Override
    public Result<SingerView> getSingerDetail(Long id) {
        GetSingerDetailCmd cmd = GetSingerDetailCmd.commandOf(id);
        return success(cmdDispatcher.dispatch(cmd));
    }

    @Override
    public Result<Void> addSinger(AddSingerCmd addSingerCmd) {
        cmdDispatcher.dispatch(addSingerCmd);
        return success();
    }

    @Override
    public Result<Void> deleteSinger(Long id) {
        cmdDispatcher.dispatch(DeleteSingerCmd.commandOf(id));
        return success();
    }

    @Override
    public Result<Void> updateSinger(UpdateSingerCmd cmd) {
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<PageResponse<SingerView>> getSinger(String name,
                                                      String sex,
                                                      int pageNo,
                                                      int pageSize) {
        SingerQuery singerQuery = SingerQuery.commandOf(name, sex);
        singerQuery.setPageNo(pageNo);
        singerQuery.setPageSize(pageSize);
        PageResponse<SingerView> results = singerService.query(singerQuery);
        return success(results);
    }

    @Override
    public Result<TotalView> getTotal(String name, String sex, int pageSize) {
        SingerQuery singerQuery = SingerQuery.commandOf(name, sex);
        singerQuery.setPageSize(pageSize);
        return success(singerService.getPageTotal(singerQuery));
    }

}
