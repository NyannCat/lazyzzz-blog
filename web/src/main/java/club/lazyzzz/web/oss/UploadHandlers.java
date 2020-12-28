package club.lazyzzz.web.oss;

import club.lazyzzz.web.exception.FileUploadException;
import club.lazyzzz.web.model.entity.Attachment;
import club.lazyzzz.web.model.enums.AttachmentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class UploadHandlers {

    private final Map<AttachmentType, UploadHandler> uploadHandlers = new ConcurrentHashMap<>(8);

    public UploadHandlers(ApplicationContext applicationContext) {
        applicationContext.getBeansOfType(UploadHandler.class)
                .values()
                .forEach(uploadHandler -> uploadHandlers.putIfAbsent(uploadHandler.getAttachmentType(), uploadHandler));
        log.info("成功注册{}个文件上传器", uploadHandlers.size());
    }

    public UploadResult upload(MultipartFile file, AttachmentType type) {
        return getSupportedHandler(type).upload(file);
    }

    public void delete(Attachment attachment) {
        getSupportedHandler(attachment.getAttachmentType()).delete(attachment.getFileKey());
    }

    private UploadHandler getSupportedHandler(AttachmentType type) {
        UploadHandler handler = uploadHandlers.get(type);
        if (handler == null) {
            throw new FileUploadException("该文件上传器配置失效或不存在").setErrorData(type);
        }
        return handler;
    }
}
