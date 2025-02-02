package io.gitee.pkmer.music.domain.singer;

import io.gitee.pkmer.ddd.shared.ValueObject;

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
public enum Sex implements ValueObject {

    FEMALE((byte)0),
    MALE((byte)1),
    UNKNOWN((byte)2); // UNKNOWN目前对应的是乐队

    private final byte value;

    Sex(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }

    public static Sex valueOf(byte value) {
        for (Sex sex : values()) {
            if (sex.value == value) {
                return sex;
            }
        }
        return UNKNOWN;
    }
}
