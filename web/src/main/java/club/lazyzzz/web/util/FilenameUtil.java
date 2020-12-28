package club.lazyzzz.web.util;

import javax.annotation.Nonnull;
import java.util.UUID;

public class FilenameUtil {
    public static String randomUUIDWithoutDash() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getFileExtension(@Nonnull String filename) {
        return filename.substring(filename.lastIndexOf(".") + 1);
    }
}
