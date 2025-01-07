package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.songlist.add.AddSongListCmd;
import io.gitee.pkmer.music.application.songlist.delete.DeleteSongListCmd;
import io.gitee.pkmer.music.application.songlist.get.SongListDto;
import io.gitee.pkmer.music.application.songlist.get.SongListPageQueryCmd;
import io.gitee.pkmer.music.web.songlist.converter.AddSongListConverter;
import io.gitee.pkmer.music.web.songlist.req.AddSongListReq;
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

    @Setter(onMethod_ = @Autowired)
    private AddSongListConverter songListConverter;

    @Override
    public Result<Void> delete(String id) {
        Long id_ = 0L;
        try{
            id_ = Long.parseLong(id);
        }catch (Exception e){
            return Result.error("id不正确");
        }
        DeleteSongListCmd cmd = DeleteSongListCmd.commandOf(id_);
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> addSongList(AddSongListReq songList) {
        AddSongListCmd cmd = songListConverter.convertFrom(songList);
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<PageResponse<SongListDto>> pageQuery(SongListPageQueryCmd cmd) {
        logger.info("cmd:{}",cmd);
        return success(cmdDispatcher.dispatch(cmd));
    }



}
