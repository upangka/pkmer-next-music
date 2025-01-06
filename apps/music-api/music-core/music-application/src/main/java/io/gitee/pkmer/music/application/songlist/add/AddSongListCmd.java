package io.gitee.pkmer.music.application.songlist.add;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/6
 */
@Getter
@AllArgsConstructor
public final class AddSongListCmd implements Command {
    private final Long id;
    private final String title;
    private final String pic;
    private final String style;
    private final String introduction;
}
