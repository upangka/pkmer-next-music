package io.gitee.pkmer.core.application.handler;

import io.gitee.pkmer.convention.converter.TargetAndSourceConverter;
import io.gitee.pkmer.core.application.command.banner.BannerGetCmd;
import io.gitee.pkmer.core.dao.mapper.banner.BannerDynamicMapper;
import io.gitee.pkmer.core.dao.model.banner.Banner;
import io.gitee.pkmer.core.types.resp.BannerVo;
import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/2
 */
@Component
public class BannerGetCmdHandler implements CommandHandler<BannerGetCmd, List<BannerVo>> {
    private final BannerDynamicMapper bannerDynamicMapper;
    public BannerGetCmdHandler(BannerDynamicMapper bannerDynamicMapper){
        this.bannerDynamicMapper = bannerDynamicMapper;
    }
    @Override
    public List<BannerVo> execute(BannerGetCmd bannerGetCmd) {
        List<Banner> banners = bannerDynamicMapper.select(c -> c);
        return Converter.INSTANCE.toTargets(banners);
    }

    @Mapper
    public interface Converter extends TargetAndSourceConverter<BannerVo,Banner> {
        Converter INSTANCE = Mappers.getMapper(Converter.class);
    }
}
