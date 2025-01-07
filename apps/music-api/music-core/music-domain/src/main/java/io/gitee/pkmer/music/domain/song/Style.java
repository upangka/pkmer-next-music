package io.gitee.pkmer.music.domain.song;

import io.gitee.pkmer.ddd.shared.ValueObject;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Stream;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
@Slf4j
public enum Style implements ValueObject {
    ALL("全部",0),
    Chinese("华语",1),
    Cantonese("粤语",2),
    EuropeAndAmerica("欧美",3),
    JapanAndKorea("日韩",4),
    LightMusic("轻音乐",5),
    BGM("BGM",6),
    Instrumental("器乐",7);

    @Getter
    final String desc;
    final Integer value;

    Style(String desc, Integer value) {
        this.desc = desc;
        this.value = value;
    }

    static Style of(String desc){
        for (Style songListStyle : values()) {
            if (songListStyle.desc.equals(desc)) {
                return songListStyle;
            }
        }
        log.error("不存在的风格：{}",desc);
        throw new UnsupportedOperationException("不存在的风格："+desc);
    }

    /**
     * 将风格字符串转换为风格列表
     * @param stylesDesc "-"分隔的字符风格
     */
    static List<Style> toStyles(String stylesDesc){
        String[] styles = stylesDesc.split("-");
        return Stream.of(styles).map(Style::of).toList();
    }

}
