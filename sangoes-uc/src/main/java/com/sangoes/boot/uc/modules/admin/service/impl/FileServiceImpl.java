package com.sangoes.boot.uc.modules.admin.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.aspose.words.Document;
import com.sangoes.boot.common.core.componet.AliyunOSSUploader;
import com.sangoes.boot.common.exception.HandleErrorException;
import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.AuthUtils;
import com.sangoes.boot.common.utils.office.Word2PdfUtil;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.entity.File;
import com.sangoes.boot.uc.modules.admin.mapper.FileMapper;
import com.sangoes.boot.uc.modules.admin.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * <p>
 * 文件管理/附件管理 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2019-06-14
 */
@Slf4j
@Service
public class FileServiceImpl extends BaseServiceImpl<FileMapper, File> implements IFileService {


    @Autowired
    private AliyunOSSUploader ossUploader;

    /**
     * 上传文件
     *
     * @param file
     */
    @Override
    public void uploadFile(MultipartFile file) {
        // 获取文件名
        String fileName = FileUtil.mainName(file.getOriginalFilename());
        // 获取后缀
        String extName = FileUtil.extName(file.getOriginalFilename());
        // 判断类型
        if (!StrUtil.equals(extName, "docx")) {
            throw new HandleErrorException("请上传以docx结尾的word文档");
        }
        // 上传文件docx
        String docPath = ossUploader.uploadMultipartFile(file);
        // 创建目标文件
        String dirPath = System.getProperty("user.dir") + "/upload/";
        java.io.File dirFile = new java.io.File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        // 创建输出路径
        String tempPath = dirPath + fileName + ".pdf";
        // 转化pdf
        Document pdf = Word2PdfUtil.doc2pdf(docPath, tempPath);
        // 上传pdf
        String pdfPath = ossUploader.upload(new java.io.File(tempPath));
        // 写入数据库
        File storeFile = new File();
        try {
            storeFile.setTitle(fileName);
            storeFile.setExtName(extName);
            if (ObjectUtil.isNotNull(pdf)) {
                storeFile.setTotalPage(pdf.getPageCount());
            }
            storeFile.setOriginalPath(docPath);
            storeFile.setConvertPath(pdfPath);
            storeFile.setCreator(AuthUtils.getUserName());
            storeFile.setCreatorId(AuthUtils.getUserId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 保存
        this.save(storeFile);
        // 删除本地文件
        FileUtil.del(tempPath);
    }

    /**
     * 文件分页
     *
     * @param params
     * @return
     */
    @Override
    public PageData<File> selectFilePage(Map<String, Object> params) {
        return this.selectPage(new PageQuery(params));
    }
}
