package io.gitee.pkmer.music.application.songlist.update;

import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.songlist.SongListAggregate;
import io.gitee.pkmer.music.domain.songlist.SongListId;
import io.gitee.pkmer.music.domain.songlist.SongListRepository;
import io.gitee.pkmer.music.domain.songlist.StyleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    @Override
    public Void execute(UpdateSongListCmd cmd) {
        SongListAggregate songListAggregate = repository.load(new SongListId(cmd.getId()))
                .orElseThrow();
        updateSongList(songListAggregate,cmd);
        songListAggregate.toUpdate();
        repository.save(songListAggregate);
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
