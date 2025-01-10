package io.gitee.pkmer.music.application.collect;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Getter;
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
@Getter
@Value(staticConstructor = "commandOf")
public class CancelCmd implements Command {
    Long id;
}
