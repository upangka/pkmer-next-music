package io.gitee.pkmer.music.web.banner;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import io.gitee.pkmer.music.application.banner.all.BannerDto;
import io.gitee.pkmer.music.application.banner.all.GetAllCmd;
import io.gitee.pkmer.music.web.banner.resp.BannerView;
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
    private CmdDispatcher cmdDispatcher;

    @Override
    public Result<List<BannerView>> getAllBanner() {
        GetAllCmd cmd = new GetAllCmd();
        List<BannerDto> banners = cmdDispatcher.dispatch(cmd);

        List<BannerView> results = banners.stream()
                .map(banner -> new BannerView().setId(banner.getId().toString())
                .setUrl(banner.getPic())).toList();

        return success(results);
    }

}
