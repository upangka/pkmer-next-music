package io.gitee.pkmer.music.web.collect;

import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.collect.CollectQuery;
import io.gitee.pkmer.music.application.collect.CollectView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
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
@Tag(name = "收藏")
@RequestMapping("/collect")
public interface CollectApi {


    @Operation(summary = "分页查询收藏")
    @GetMapping
    Result<PageResponse<CollectView>> pageQuery(CollectQuery query);
}
