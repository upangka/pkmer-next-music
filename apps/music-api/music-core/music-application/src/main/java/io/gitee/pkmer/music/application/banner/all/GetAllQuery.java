package io.gitee.pkmer.music.application.banner.all;

import io.gitee.pkmer.ddd.shared.command.Command;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllQuery implements Command {
    private List<Long> ids = Collections.emptyList();


}
