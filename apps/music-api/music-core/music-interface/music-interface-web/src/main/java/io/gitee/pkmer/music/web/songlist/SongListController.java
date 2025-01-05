package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.songlist.delete.DeleteSongListCmd;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@RestController
public class SongListController extends BaseController implements SongListApi{

    @Setter(onMethod_ = @Autowired)
    private CmdDispatcher cmdDispatcher;

    @Override
    public Result<Void> delete(String id) {
        try{
            Long id_ = Long.parseLong(id);
            DeleteSongListCmd cmd = DeleteSongListCmd.commandOf(id_);
            cmdDispatcher.dispatch(cmd);
            return success();
        }catch (Exception e){
            return Result.error("id不正确");
        }
    }

    @Override
    public Result<Void> addSongList(SongListReq songList) {
        return null;
    }
}
