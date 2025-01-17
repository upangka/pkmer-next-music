import java.nio.file.Files;
import java.nio.file.Path;

public class ProbeContentTypeExample {
    public static void main(String[] args) {
        try {
            Path filePath = Path.of("example.mp3");
            String mimeType = Files.probeContentType(filePath);
            System.out.println("MIME Type: " + mimeType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
/**output
 * MIME Type: audio/mpeg
 */