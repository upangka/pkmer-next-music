package io.gitee.pkmer.music.application.banner.all;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(title = "bannerId",description = "主键id",defaultValue = "1")
    public String id;
    @Schema(title = "url",description = "图片链接")
    public String url;
}
