package club.lazyzzz.web.service.impl;

import club.lazyzzz.web.exception.FileUploadException;
import club.lazyzzz.web.mapper.AttachmentMapper;
import club.lazyzzz.web.model.entity.Attachment;
import club.lazyzzz.web.model.enums.AttachmentType;
import club.lazyzzz.web.oss.UploadHandlers;
import club.lazyzzz.web.oss.UploadResult;
import club.lazyzzz.web.service.IAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
@Slf4j
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {

    @Autowired
    private UploadHandlers handlers;

    @Override
    public UploadResult upload(MultipartFile file, AttachmentType type) {
        UploadResult result = handlers.upload(file, type);
        Attachment attachment = new Attachment();
        attachment.setName(result.getFilename());
        attachment.setPath(result.getFilePath());
        attachment.setFileKey(result.getKey());
        attachment.setMediaType(file.getContentType());
        attachment.setAttachmentType(type);
        baseMapper.insert(attachment);
        log.info("文件上传成功: {}", attachment);
        return result;
    }

    @Override
    public void delete(Integer id) {
        Attachment attachment = baseMapper.selectById(id);
        if (attachment == null) {
            throw new FileUploadException("文件不存在");
        }
        handlers.delete(attachment);
        baseMapper.deleteById(id);
        log.info("文件删除成功: {}", attachment);
    }
}
