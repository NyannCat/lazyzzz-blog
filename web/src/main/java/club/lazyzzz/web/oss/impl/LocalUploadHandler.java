package club.lazyzzz.web.oss.impl;

import club.lazyzzz.web.config.property.LazyzzzProperty;
import club.lazyzzz.web.exception.FileUploadException;
import club.lazyzzz.web.model.enums.AttachmentType;
import club.lazyzzz.web.oss.UploadHandler;
import club.lazyzzz.web.oss.UploadResult;
import club.lazyzzz.web.util.FilenameUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Objects;

@Slf4j
@Component
public class LocalUploadHandler implements UploadHandler {

    private final String uploadDir;

    public LocalUploadHandler(LazyzzzProperty lazyzzzProperty) {
        this.uploadDir = lazyzzzProperty.getUploadDir();
        checkWorkDir();
        log.info("本地文件上传器注册成功");
    }

    private void checkWorkDir() {
        Path workPath = Paths.get(uploadDir);
        try {
            Files.createDirectories(workPath);
        } catch (IOException e) {
            throw new FileUploadException("本地上传文件夹创建失败").setErrorData(workPath);
        }
        Assert.isTrue(Files.isDirectory(workPath), uploadDir + " isn't a directory");
        Assert.isTrue(Files.isReadable(workPath), uploadDir + " isn't a readable");
        Assert.isTrue(Files.isWritable(workPath), uploadDir + " isn't a writable");
    }

    @Nonnull
    @Override
    public UploadResult upload(@Nonnull MultipartFile file) {
        Assert.notNull(file.getOriginalFilename(), "文件名不能为空");

        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonth().getValue();
        //根据年份+月份对上传文件进行分类
        String subDir = year + File.separator + month + File.separator;
        //文件拓展名
        String ext = FilenameUtil.getFileExtension(file.getOriginalFilename());

        log.debug("Original Name {}, Extension {}", file.getName(), ext);

        //真实存储的文件名
        String subFilePath = subDir + FilenameUtil.randomUUIDWithoutDash() + "." + ext;
        //真实路径
        Path uploadPath = Paths.get(uploadDir, subFilePath);

        try {
            //创建父文件夹
            Files.createDirectories(uploadPath.getParent());
            //创建文件
            Files.createFile(uploadPath);
            //真正执行上传操作
            file.transferTo(uploadPath);

            UploadResult result = new UploadResult();
            result.setFilename(file.getOriginalFilename());
            result.setFilePath("/uploads/" + subFilePath);
            result.setKey(subFilePath);
            result.setMediaType(MediaType.valueOf(Objects.requireNonNull(file.getContentType())));
            result.setSize(file.getSize());

            return result;
        } catch (IOException e) {
            log.error("本地附件上传失败", e);
            throw new FileUploadException("本地附件上传失败").setErrorData(uploadPath);
        }
    }

    @Override
    public void delete(@Nonnull String key) {
        Path path = Path.of(uploadDir, key);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error("附件删除失败", e);
            throw new FileUploadException("文件删除失败").setErrorData(e);
        }
    }

    @Override
    public AttachmentType getAttachmentType() {
        return AttachmentType.LOCAL;
    }
}
