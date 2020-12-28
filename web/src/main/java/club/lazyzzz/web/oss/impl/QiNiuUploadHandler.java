package club.lazyzzz.web.oss.impl;

import club.lazyzzz.web.config.property.LazyzzzProperty;
import club.lazyzzz.web.config.property.QiNiuProperty;
import club.lazyzzz.web.exception.FileUploadException;
import club.lazyzzz.web.model.enums.AttachmentType;
import club.lazyzzz.web.oss.UploadHandler;
import club.lazyzzz.web.oss.UploadResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@ConditionalOnProperty(name = "lazyzzz.qi-niu-property.access-key")
public class QiNiuUploadHandler implements UploadHandler {

    private final QiNiuProperty qiNiuProperty;
    private final UploadManager uploadManager;
    private final BucketManager bucketManager;
    private final Auth auth;

    private final ObjectMapper mapper;

    public QiNiuUploadHandler(LazyzzzProperty property,
                              ObjectMapper mapper) {
        this.qiNiuProperty = property.getQiNiuProperty();
        this.mapper = mapper;
        this.auth = Auth.create(qiNiuProperty.getAccessKey(), qiNiuProperty.getSecretKey());
        Configuration cfg = new Configuration(Region.autoRegion());
        this.uploadManager = new UploadManager(cfg);
        this.bucketManager = new BucketManager(auth, cfg);
        log.info("七牛云文件上传器注册成功");
    }

    @Nonnull
    @Override
    public UploadResult upload(@Nonnull MultipartFile file) {
        Assert.notNull(file.getOriginalFilename(), "文件名不能为空");

        String token = auth.uploadToken(qiNiuProperty.getBucketName());
        Assert.hasText(token, "token获取失败");

        try (var input = file.getInputStream()) {
            String key = file.getOriginalFilename();
            Response response = uploadManager.put(input, key, token, null, null);
            DefaultPutRet defaultPutRet = mapper.readValue(response.bodyString(), DefaultPutRet.class);

            UploadResult result = new UploadResult();
            result.setKey(defaultPutRet.key);
            result.setFilePath(qiNiuProperty.getDomain() + defaultPutRet.key);
            result.setSize(file.getSize());
            result.setFilename(file.getOriginalFilename());
            result.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())));
            return result;
        } catch (IOException e) {
            log.error("七牛云附件上传失败", e);
            throw new FileUploadException("七牛云附件上传失败").setErrorData(e);
        }
    }

    @Override
    public void delete(@Nonnull String key) {
        try {
            bucketManager.delete(qiNiuProperty.getBucketName(), key);
        } catch (QiniuException e) {
            log.error("七牛云附件删除失败", e);
            throw new FileUploadException("七牛云附件删除失败").setErrorData(e);
        }
    }

    @Override
    public AttachmentType getAttachmentType() {
        return AttachmentType.QI_NIU_YUN;
    }
}
