package io.gitee.pkmer.music.web.banner;

import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.web.banner.resp.BannerView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@RequestMapping("banner")
public interface BannerApi {

    /**
     * 获取所有轮播图
     */
    @GetMapping
    Result<List<BannerView>> getAllBanner();
}
