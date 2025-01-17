package io.gitee.pkmer.minio.utils;

import java.util.Map;


public class TimeMinutesUtil {
    // 定义单位与倍数的映射关系
    private static final Map<String, Integer> TIME_UNITS = Map.of(
            "h", 60,
            "m", 1
    );

    public static int getTime(String time) {
        String timeFormatter = time.trim().toLowerCase();

        for (Map.Entry<String, Integer> entry : TIME_UNITS.entrySet()) {
            if (timeFormatter.endsWith(entry.getKey())) {
                return parseTime(timeFormatter, entry.getKey(), entry.getValue());
            }
        }

        throw new IllegalArgumentException("不支持的时间单位：" + time + "以h或者m,如1h或者60m");
    }

    private static int parseTime(String timeFormatter, String unit, int multiplier) {
        String timeValue = timeFormatter.substring(0, timeFormatter.length() - unit.length());
        return Integer.parseInt(timeValue) * multiplier;
    }
}
