package io.gitee.pkmer.music.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class MultiPartFileHelper {

    public  String readFileContent(MultipartFile file) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n"); // 每行内容加入字符串，并添加换行符
            }
        } catch (IOException e) {
            throw new RuntimeException("文件读取失败");
        }
        return content.toString();
    }
}
