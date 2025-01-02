package io.gitee.pkmer.core.application.command.banner;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.Value;

/**
 * {@link io.gitee.pkmer.core.application.handler.BannerGetCmdHandler}
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/2
 */
@Value(staticConstructor = "commandOf")
public class BannerGetCmd implements Command {
}
