package io.gitee.pkmer.music.web.collect;


import io.gitee.common.view.TotalView;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.core.infrastructure.persistence.collect.mybatis.CollectSongDto;
import io.gitee.pkmer.music.application.collect.CollectQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "admin收藏管理")
@RequestMapping("/admin/collect")
@Validated
public interface AdminCollectApi {

    @Operation(summary = "取消用户的收藏歌曲")
    @Parameters({
            @Parameter(name = "userId", description = "歌曲userId", in = ParameterIn.QUERY),
            @Parameter(name = "songId", description = "歌曲songId", in = ParameterIn.QUERY)
    })
    @DeleteMapping("/song")
    Result<Void> cancelCollectSong(
            @RequestParam("userId") @NotNull(message = "用户ID不能为空")
            Long userId,
            @RequestParam("songId") @NotNull(message = "歌曲ID不能为空")
            Long songId);

    @Operation(summary = "管理员端分页查询收藏")
    @PostMapping("/page")
    Result<PageResponse<CollectSongDto>> adminPageQuery(@RequestBody CollectQuery query);


    @Operation(summary = "获取收藏总数")
    @PostMapping("/page/total")
    Result<TotalView> getPageTotal(@RequestBody CollectQuery query);
}
