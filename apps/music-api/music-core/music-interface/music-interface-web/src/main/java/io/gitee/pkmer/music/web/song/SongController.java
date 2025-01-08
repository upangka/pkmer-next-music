package io.gitee.pkmer.music.web.song;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.song.delete.DeleteCmd;
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
 * @since 2025/1/9
 * </p>
 */
@RestController
public class SongController extends BaseController implements SongApi{
    @Setter(onMethod_ = @Autowired)
    private CmdDispatcher cmdDispatcher;
    @Override
    public Result<Void> deleteSong(Long id) {
        cmdDispatcher.dispatch(DeleteCmd.commandOf(id));
        return success();
    }
}
