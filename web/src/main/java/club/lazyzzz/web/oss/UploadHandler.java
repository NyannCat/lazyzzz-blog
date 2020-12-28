package club.lazyzzz.web.oss;

import club.lazyzzz.web.exception.FileUploadException;
import club.lazyzzz.web.model.enums.AttachmentType;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;

public interface UploadHandler {

    MediaType IMAGE_TYPE = MediaType.valueOf("image/**");

    /**
     * 上传文件将
     * @param file multipart
     * @return 上传结果
     */
    @Nonnull
    UploadResult upload(@Nonnull MultipartFile file);

    /**
     * 删除文件
     * @param key 文件对应的key
     */
    void delete(@Nonnull String key);

    /**
     * 返回上传的类型
     * @return 上传类型（本地，七牛云，腾讯云，阿里云...）
     */
    AttachmentType getAttachmentType();

    /**
     * 检测文件是否是图片类型
     * @param file multipart
     * @return 是否是图片类型
     */
    default boolean isImageType(@Nonnull MultipartFile file) {
        String mediaType = file.getContentType();
        return mediaType != null && IMAGE_TYPE.includes(MediaType.valueOf(mediaType));
    }
}
