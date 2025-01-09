package io.gitee.pkmer.music.web.singer;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.singer.add.AddSingerCmd;
import io.gitee.pkmer.music.application.singer.query.SingerView;
import io.gitee.pkmer.music.application.singer.update.UpdateSingerCmd;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

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
@Tag(name = "歌手")
@RequestMapping("/singer")
public interface SingerApi {

    @Operation(summary = "添加歌手")
    @PostMapping("/add")
    Result<Void> addSinger(@Valid
                           @RequestBody
                           AddSingerCmd addSingerCmd);

    @Operation(summary = "删除歌手")
    @DeleteMapping("/{id}")
    Result<Void> deleteSinger(@PathVariable("id") Long id);

    @Operation(summary = "添加歌手")
    @PostMapping("/update")
    Result<Void> updateSinger(@Valid @RequestBody
                              UpdateSingerCmd cmd);


    @Operation(summary = "查询歌手")
    @Parameters({
            @Parameter(name = "name",description = "歌手名称",in = ParameterIn.PATH),
            @Parameter(name = "sex",description = "歌手性别",example = "MALE" ,required = true,in = ParameterIn.HEADER),
    })
    @GetMapping("/query")
    Result<PageResponse<SingerView>> getSinger(@RequestParam(value = "name",required = false)
                           String name,
                                               @RequestParam(value = "sex",required = false)
                           String sex);
}
