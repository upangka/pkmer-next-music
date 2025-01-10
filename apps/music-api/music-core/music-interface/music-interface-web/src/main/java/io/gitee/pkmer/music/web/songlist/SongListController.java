package io.gitee.pkmer.music.web.songlist;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.songlist.add.AddCommentCmd;
import io.gitee.pkmer.music.application.songlist.add.AddSongForListCmd;
import io.gitee.pkmer.music.application.songlist.add.AddSongListCmd;
import io.gitee.pkmer.music.application.songlist.delete.DeleteCommentCmd;
import io.gitee.pkmer.music.application.songlist.delete.DeleteSongForListCmd;
import io.gitee.pkmer.music.application.songlist.delete.DeleteSongListCmd;
import io.gitee.pkmer.music.application.songlist.query.SongListPageQuery;
import io.gitee.pkmer.music.application.songlist.query.SongListService;
import io.gitee.pkmer.music.application.songlist.query.SongListView;
import io.gitee.pkmer.music.application.songlist.update.UpdateSongListCmd;
import io.gitee.pkmer.music.application.songlist.update.UpdateSongListCommentCmd;
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

    @Setter(onMethod_ = @Autowired)
    private SongListService songListService;

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
    public Result<PageResponse<SongListView>> pageQuery(SongListPageQuery pageQuery) {
        return success(songListService.pageQuery(pageQuery));
    }

    @Override
    public Result<Void> updateSongListBase(UpdateSongListCmd cmd) {
        cmd.toBaseInfo();
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> updateSongListPic(UpdateSongListCmd cmd) {
        cmd.toUpdatePic();
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> addSongToSongList(AddSongForListCmd cmd) {
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> deleteSongToSongList(DeleteSongForListCmd cmd) {
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> updateSongListComment(UpdateSongListCommentCmd cmd) {
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> deleteSongListComment(DeleteCommentCmd cmd) {
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> addSongListComment(AddCommentCmd cmd) {
        cmdDispatcher.dispatch(cmd);
        return success();
    }
}
