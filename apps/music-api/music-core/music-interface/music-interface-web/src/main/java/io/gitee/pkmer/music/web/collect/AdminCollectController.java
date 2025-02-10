package io.gitee.pkmer.music.web.collect;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.collect.CancelCmd;
import jakarta.annotation.Resource;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminCollectController
        extends BaseController
        implements AdminCollectApi {


    @Setter(onMethod_ = @Resource)
    private CmdDispatcher cmdDispatcher;

    @Override
    public Result<Void> cancelCollectSong(Long userId, Long songId) {
        CancelCmd cmd = CancelCmd.commandOf(userId, songId, null);
        cmdDispatcher.dispatch(cmd);
        return success();
    }

}
