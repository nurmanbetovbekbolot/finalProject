package kg.itacademy.gsg.utils;

import javax.servlet.ServletContext;
import org.springframework.http.MediaType;
import java.io.*;

public class MediaTypeUtils {

    public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        String mineType = servletContext.getMimeType(fileName);
        try {
            MediaType mediaType = MediaType.parseMediaType(mineType);
            return mediaType;
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }

    public static File writeByte(byte[] bytes, String path) {
        File file = new File(path);
        try {
            OutputStream os = new FileOutputStream(file);
            os.write(bytes);
            os.close();
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        return file;
    }
}