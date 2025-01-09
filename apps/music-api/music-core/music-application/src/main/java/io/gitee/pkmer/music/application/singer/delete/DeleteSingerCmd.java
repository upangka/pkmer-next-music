package io.gitee.pkmer.music.application.singer.delete;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Data;
import lombok.Value;

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
@Data
@Value(staticConstructor = "commandOf")
public class DeleteSingerCmd implements Command {
    private final Long id;
}
