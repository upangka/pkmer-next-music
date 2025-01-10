package io.gitee.pkmer.music.web.songlist.converter;

import io.gitee.pkmer.music.application.songlist.add.AddSongListCmd;
import io.gitee.pkmer.music.web.songlist.req.AddSongListReq;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/6
 */
public interface AddSongListConverter {
    AddSongListCmd convertFrom(AddSongListReq req);
}
