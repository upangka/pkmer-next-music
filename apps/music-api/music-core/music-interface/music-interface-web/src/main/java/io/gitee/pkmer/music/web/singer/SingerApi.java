package io.gitee.pkmer.music.web.singer;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.singer.add.AddSingerCmd;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
@RequestMapping("/singer")
public interface SingerApi {

    @Operation(summary = "添加歌手")
    @PostMapping("/add")
    Result<Void> addSinger(@Valid
                           @RequestBody
                           AddSingerCmd addSingerCmd);
}
