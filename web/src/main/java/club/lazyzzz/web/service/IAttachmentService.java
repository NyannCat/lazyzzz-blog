package club.lazyzzz.web.service;

import club.lazyzzz.web.model.entity.Attachment;
import club.lazyzzz.web.model.enums.AttachmentType;
import club.lazyzzz.web.oss.UploadResult;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lazyzzz
 * @since 2020-12-17
 */
public interface IAttachmentService extends IService<Attachment> {
    /**
     * 上传附件
     * @param file multipart
     * @param type 上传方式
     * @return 上传结果
     */
    UploadResult upload(MultipartFile file, AttachmentType type);

    /**
     * 删除文件
     * @param id 附件id
     */
    void delete(Integer id);
}
