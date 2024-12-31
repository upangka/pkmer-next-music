package io.gitee.pkmer;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author <a href="mailto:1193094618@qq.com">pkmer</a>
 * <br>
 * <a href = "https://gitee.com/developeros/videos-online">Code Repository</a>
 * At 2025/1/1
 */
public class TestUrl {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String fileName = "Summer Dance & Ya! 现场版 李贞贤.mp3";
        String decode = URLEncoder.encode(fileName, StandardCharsets.UTF_8);;
        System.out.println(decode);
        String encode = URLDecoder.decode(decode);
        System.out.println(encode);
        System.out.println("给前端用的编码");
        System.out.println(decode.replace("+", "%20"));
    }
}
