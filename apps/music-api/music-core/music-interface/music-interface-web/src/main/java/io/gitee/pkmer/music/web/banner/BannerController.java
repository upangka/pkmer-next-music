package io.gitee.pkmer.music.web.banner;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.music.application.banner.all.BannerService;
import io.gitee.pkmer.music.application.banner.all.BannerView;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * At 2025/1/5
 */
@RestController
public class BannerController extends BaseController implements BannerApi {

    @Setter(onMethod_ = @Autowired)
    private BannerService bannerService;

    @Override
    public Result<List<BannerView>> getAllBanner() {
        return success(bannerService.findAll(List.of()));
    }

}
