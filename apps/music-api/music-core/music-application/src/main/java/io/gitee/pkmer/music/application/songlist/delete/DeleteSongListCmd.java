package io.gitee.pkmer.music.application.songlist.delete;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Value;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Getter
@Value(staticConstructor = "commandOf")
public class DeleteSongListCmd implements Command {
    Long id;
}
