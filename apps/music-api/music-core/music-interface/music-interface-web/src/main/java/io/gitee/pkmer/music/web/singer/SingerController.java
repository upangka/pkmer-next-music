package io.gitee.pkmer.music.web.singer;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.singer.add.AddSingerCmd;
import io.gitee.pkmer.music.application.singer.delete.DeleteSingerCmd;
import io.gitee.pkmer.music.application.singer.query.SingerQuery;
import io.gitee.pkmer.music.application.singer.query.SingerService;
import io.gitee.pkmer.music.application.singer.query.SingerView;
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
    public Result<PageResponse<SingerView>> getSinger(String name, String sex) {
        SingerQuery singerQuery = SingerQuery.commandOf(name, sex);
        PageResponse<SingerView> results = singerService.query(singerQuery);
        return success(results);
    }

}
