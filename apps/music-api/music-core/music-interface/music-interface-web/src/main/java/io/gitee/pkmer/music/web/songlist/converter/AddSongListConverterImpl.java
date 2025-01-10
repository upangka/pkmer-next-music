package io.gitee.pkmer.music.web.songlist.converter;

import io.gitee.pkmer.music.application.songlist.add.AddSongListCmd;
import io.gitee.pkmer.music.web.songlist.req.AddSongListReq;
import org.springframework.stereotype.Component;

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
@Component
public class AddSongListConverterImpl implements AddSongListConverter {

    @Override
    public AddSongListCmd convertFrom(AddSongListReq req) {
        if ( req == null ) {
            return null;
        }

        Long id = null;
        String title = null;
        String pic = null;
        String style = null;
        String introduction = null;

        if ( req.getId() != null ) {
            id = req.getId().longValue();
        }
        title = req.getTitle();
        pic = req.getPic();
        style = req.getStyle();
        introduction = req.getIntroduction();

        AddSongListCmd addSongListCmd = new AddSongListCmd( id, title, pic, style, introduction );

        return addSongListCmd;
    }
}