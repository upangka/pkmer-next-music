package io.gitee.pkmer.music.application.banner.all;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Data
@Accessors(chain = true)
public class BannerView {
    private Long id;
    private String pic;
}
