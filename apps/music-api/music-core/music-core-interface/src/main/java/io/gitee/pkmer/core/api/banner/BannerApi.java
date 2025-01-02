package io.gitee.pkmer.core.api.banner;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.core.types.resp.BannerVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/2
 */

@RequestMapping("/banner")
public interface BannerApi {

    @GetMapping
    Result<List<BannerVo>> getAllBanner();
}
