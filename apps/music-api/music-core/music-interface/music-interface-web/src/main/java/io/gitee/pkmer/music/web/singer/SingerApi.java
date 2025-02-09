package io.gitee.pkmer.music.web.singer;

import io.gitee.common.view.TotalView;
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

    @Operation(summary = "查询歌手详情")
    @Parameters({
            @Parameter(name = "id", description = "歌手id", required = true, in = ParameterIn.PATH),
    })
    @GetMapping("/{id}")
    Result<SingerView> getSingerDetail(@PathVariable("id") Long id);

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
            @Parameter(name = "name", description = "歌手名称", in = ParameterIn.PATH),
            @Parameter(name = "sex", description = "歌手性别", example = "MALE", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "pageNo", description = "页码", example = "1", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "pageSize", description = "每页大小", example = "10", required = true, in = ParameterIn.HEADER),
    })
    @GetMapping("/page")
    Result<PageResponse<SingerView>> getSinger(
            @RequestParam(value = "name", required = false)
            String name,
            @RequestParam(value = "sex", required = false)
            String sex,
            @RequestParam(value = "pageNo", required = false, defaultValue = "1")
            int PageNo,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            int pageSize
    );

    @Operation(summary = "获取歌手分页总数")
    @Parameters({
            @Parameter(name = "name", description = "歌手名称", in = ParameterIn.PATH),
            @Parameter(name = "sex", description = "歌手性别", example = "MALE", required = true, in = ParameterIn.HEADER),
    })
    @GetMapping("/page/total")
    Result<TotalView> getTotal(
            @RequestParam(value = "name", required = false)
            String name,
            @RequestParam(value = "sex", required = false)
            String sex,
            @Parameter(name = "pageSize", description = "每页大小", example = "10", required = true, in = ParameterIn.HEADER)
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            int pageSize
    );
}
