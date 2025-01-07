package io.gitee.pkmer.music.application.songlist.update;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.song.SongListAggregate;
import io.gitee.pkmer.music.domain.song.SongListId;
import io.gitee.pkmer.music.domain.song.SongListRepository;
import io.gitee.pkmer.music.domain.song.StyleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/7
 * </p>
 */
@Slf4j
@Component
public class UpdateSongListHandler implements CommandHandler<UpdateSongListCmd,Void> {

    private final SongListRepository repository;
    private final  StyleValidator styleValidator;
    public UpdateSongListHandler(SongListRepository repository, StyleValidator styleValidator) {
        this.repository = repository;
        this.styleValidator = styleValidator;
    }


    @Transactional
    @Override
    public Void execute(UpdateSongListCmd cmd) {
        SongListId id = new SongListId(cmd.getId());
        SongListAggregate songListAggregate = repository.load(id);
        if(songListAggregate != null){
            updateSongList(songListAggregate,cmd);
            songListAggregate.toUpdate();
            repository.save(songListAggregate);
        }
        return null;
    }


    private void updateSongList(SongListAggregate songListAggregate,UpdateSongListCmd cmd){

        if(cmd.isToModifyPic()){
            songListAggregate.modifyPic(cmd.getPic());
        }else{
            updateBaseInfo(songListAggregate,cmd);
        }

    }


    private void updateBaseInfo(SongListAggregate songListAggregate,UpdateSongListCmd cmd){
        log.info("更新歌单基本信息");
        String styles = cmd.getStyles();
        if(!styles.trim().isEmpty()){
            styleValidator.validate(styles);
            songListAggregate.modifyStyles(styles);
        }

        songListAggregate.modifyIntroduction(cmd.getIntroduction());
        songListAggregate.modifyPic(cmd.getPic());
        songListAggregate.modifyTitle(cmd.getTitle());
    }
}
