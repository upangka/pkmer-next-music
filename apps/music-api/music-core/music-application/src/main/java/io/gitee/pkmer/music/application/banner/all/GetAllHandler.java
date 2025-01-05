package io.gitee.pkmer.music.application.banner.all;


import io.gitee.pkmer.ddd.shared.command.CommandHandler;
import io.gitee.pkmer.music.domain.banner.BannerEntity;
import io.gitee.pkmer.music.domain.banner.BannerRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Slf4j
@Component
public class GetAllHandler implements CommandHandler<GetAllCmd, List<BannerDto>>, EnvironmentAware {
    private String minioUrl;
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
                    .setPic(buildMinioUrl(banner.getPic()));
            results.add(bannerDto);
        }
        return results;
    }

    private String buildMinioUrl(String objectName){
        return minioUrl + "/" + objectName;
    }

    @Override
    public void setEnvironment(Environment environment) {
        String url = environment.getProperty("pkmer.minio.url");
        if (url == null || url.isBlank()){
            log.error("通过 pkmer.minio.url 获取 minio url为空");
            throw new RuntimeException("minio url is null");
        }else{
            log.info("minio url 设置为 {}",url);
            this.minioUrl = url;
        }
    }
}
