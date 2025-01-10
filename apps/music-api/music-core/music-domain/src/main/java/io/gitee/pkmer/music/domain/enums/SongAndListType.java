package io.gitee.pkmer.music.domain.enums;

import io.gitee.pkmer.ddd.shared.ValueObject;
import lombok.Getter;

/**
 * <p>
 *
 * @author <a href="mailto:3149374525@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/pkmer">Code Repository</a>
 * @author pkmer
 * @since 2025/1/10
 * </p>
 */
@Getter
public enum SongAndListType implements ValueObject {

    SONG_LIST("song_list", (byte) 0),

    SONG("song", (byte) 1);

    private final String desc;
    private final Byte value;

    SongAndListType(String desc, Byte value ) {
        this.value = value;
        this.desc = desc;
    }

    public static SongAndListType valueOf(Byte value) {
        for (SongAndListType songAndList : values()) {
            if (songAndList.getValue().equals(value)) {
                return songAndList;
            }
        }
       throw new UnsupportedOperationException("不支持的类型");
    }

}
