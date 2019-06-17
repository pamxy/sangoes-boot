package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 文件管理/附件管理 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2019-06-14
 */
public interface IFileService extends IBaseService<File> {

    /**
     * 上传文件
     *
     * @param file
     */
    void uploadFile(MultipartFile file);

    /**
     * 文件分页
     *
     * @param params
     * @return
     */
    PageData<File> selectFilePage(Map<String, Object> params);
}
