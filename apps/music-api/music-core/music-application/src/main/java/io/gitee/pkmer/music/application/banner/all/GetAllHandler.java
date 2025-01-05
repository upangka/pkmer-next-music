package io.gitee.pkmer.music.application.banner.all;


import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.banner.BannerEntity;
import io.gitee.pkmer.music.domain.banner.BannerRepository;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Component
public class GetAllHandler implements CommandHandler<GetAllCmd, List<BannerDto>> {
    private final  BannerRepository bannerRepository;

    public GetAllHandler(BannerRepository bannerRepository){
        this.bannerRepository = bannerRepository;
    }

    @Override
    public List<BannerDto> execute(GetAllCmd cmd) {
        List<BannerEntity> banners = bannerRepository.findAll(cmd.getIds());

        List<BannerDto> results = new ArrayList<>();
        for (BannerEntity banner : banners) {
            BannerDto bannerDto = new BannerDto().setId(banner.getId().value())
                    .setPic(banner.getPic());
            results.add(bannerDto);
        }
        return results;
    }

}
