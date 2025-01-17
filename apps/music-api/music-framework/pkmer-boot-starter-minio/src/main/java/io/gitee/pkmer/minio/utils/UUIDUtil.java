package io.gitee.pkmer.minio.utils;
import java.util.UUID;
public class UUIDUtil {
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
