package io.gitee.pkmer.music.web.song;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.collect.CollectCmd;
import io.gitee.pkmer.music.application.song.delete.DeleteCmd;
import io.gitee.pkmer.music.application.song.get.GetSongCmd;
import io.gitee.pkmer.music.application.song.get.SongDetailView;
import io.gitee.pkmer.music.application.song.update.UpdateSongCmd;
import io.gitee.pkmer.music.utils.MultiPartFileHelper;
import io.gitee.pkmer.security.context.AppContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
@RequiredArgsConstructor
@RestController
@Slf4j
public class SongController extends BaseController implements SongApi{
    private final  CmdDispatcher cmdDispatcher;
    private final MultiPartFileHelper multiPartFileHelper;
    @Override
    public Result<Void> deleteSong(Long id) {
        cmdDispatcher.dispatch(DeleteCmd.commandOf(id));
        return success();
    }

    @Override
    public Result<Void> updateSong(UpdateSongCmd cmd, MultipartFile lyricFile) {
        if(!lyricFile.isEmpty()){
            String lyricContent = multiPartFileHelper.readFileContent(lyricFile);
            log.info("读取的歌词文件内容 => {}",lyricContent);
            cmd.setLyric(lyricContent);
        }

        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<Void> collectSongList(Long songId) {
        Long userId = AppContextHolder.userContextHolder
                .getUser()
                .getId();
        CollectCmd cmd = new CollectCmd()
                .setSongId(songId)
                .setUserId(userId);
        cmdDispatcher.dispatch(cmd);
        return success();
    }

    @Override
    public Result<SongDetailView> getSongDetail(Long id) {
        GetSongCmd cmd = GetSongCmd.commandOf(id);
        return success(cmdDispatcher.dispatch(cmd));
    }
}
