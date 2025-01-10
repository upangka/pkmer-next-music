package io.gitee.pkmer.music.web.collect;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.page.PageResponse;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.collect.CollectQuery;
import io.gitee.pkmer.music.application.collect.CollectService;
import io.gitee.pkmer.music.application.collect.CollectView;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
public class CollectController extends BaseController implements CollectApi {

    private final CollectService service;

    public CollectController(CollectService service) {
        this.service = service;
    }

    @Override
    public Result<PageResponse<CollectView>> pageQuery(CollectQuery query) {
        // todo 处理用户登录后那id
        return success(service.pageQuery(query));
    }
}
