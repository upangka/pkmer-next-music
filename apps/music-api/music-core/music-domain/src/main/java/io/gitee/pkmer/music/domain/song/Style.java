package io.gitee.pkmer.music.domain.song;

import io.gitee.pkmer.ddd.shared.ValueObject;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/5
 */
public enum Style implements ValueObject {
    ALL("全部",0),
    Chinese("中文",1),
    Cantonese("粤语",2),
    EuropeAndAmerica("欧美",3),
    JapanAndKorea("日韩",4),
    LightMusic("轻音乐",5),
    BGM("BGM",6),
    Instrumental("器乐",7);

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
        throw new UnsupportedOperationException("不存在的风格："+desc);
    }

}
