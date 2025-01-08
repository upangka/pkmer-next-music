package io.gitee.pkmer.music.application.song.delete;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Value;

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
@Value(staticConstructor = "commandOf")
public class DeleteCmd implements Command {
    Long id;
}
