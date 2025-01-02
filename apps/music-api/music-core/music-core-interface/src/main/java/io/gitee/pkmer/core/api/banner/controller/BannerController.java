package io.gitee.pkmer.core.api.banner.controller;

import io.gitee.pkmer.convention.controller.BaseController;
import io.gitee.pkmer.convention.result.Result;
import io.gitee.pkmer.core.api.banner.BannerApi;
import io.gitee.pkmer.core.application.command.banner.BannerGetCmd;
import io.gitee.pkmer.core.types.resp.BannerVo;
import io.gitee.pkmer.ddd.shared.dispatch.CmdDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 *
 * <br>
 * At 2025/1/2
 */
@RestController
public class BannerController extends BaseController implements BannerApi {
    @Autowired
    private CmdDispatcher cmdDispatcher;
    @Override
    public Result<List<BannerVo>> getAllBanner() {
        logger.info("get all banner");
        return success(cmdDispatcher.dispatch(BannerGetCmd.commandOf()));
    }
}
