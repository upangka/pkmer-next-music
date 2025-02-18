package io.gitee.pkmer.music.domain.singer;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;

public class SexDeserializer extends JsonDeserializer<Sex> {
    @Override
    public Sex deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = p.getIntValue(); // 读取数字值
        return Sex.valueOf((byte) value); // 转换为对应的枚举值
    }
}
