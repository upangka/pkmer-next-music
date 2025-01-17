import java.net.FileNameMap;
import java.net.URLConnection;

public class MimeTypeDemo {
    public static void main(String[] args) {
        String fileName = "music.mp3";
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String mimeType = fileNameMap.getContentTypeFor(fileName);

        System.out.println("File: " + fileName);
        System.out.println("MIME Type: " + (mimeType != null ? mimeType : "Unknown"));
    }
}
/**
 * File: music.mp3
 * MIME Type: audio/mpeg
 */
