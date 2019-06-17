package com.sangoes.boot.common.utils.office;


import cn.hutool.core.io.FileUtil;
import com.aspose.words.Document;
import com.aspose.words.SaveFormat;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 * <p>
 * word 转 pdf
 *
 * @author jerrychir
 */
@Slf4j
public class Word2PdfUtil {
    public static void main(String[] args) {
        doc2pdf("https://nickms.oss-cn-beijing.aliyuncs.com/test.docx?Expires=1560510703&OSSAccessKeyId=TMP.AgGtVUaHBxlwDNGdAQA4wO3xXtY2lBHXtEaGAPaoVA9gSSQME6oSqwcJ4XZXADAtAhUAyGq6mUL1owyAjZdQkTGMVeKm1o0CFDff0FNteXm422A6RDT76lT8bw58&Signature=n%2BUduy0YCv7%2Bs1BpAnlo%2FVo%2FIDE%3D", "/Users/jerrychir/Desktop/test.pdf");
        String fileName = "/Users/jerrychir/Desktop/test.docx";
        String name = FileUtil.getName(fileName);
        String extName = FileUtil.extName(fileName);
        String type = FileUtil.getType(new File(fileName));
        log.info("name:{}", name);
        log.info("type:{}", type);
        log.info("extName:{}", extName);

    }

    /**
     * word 转 pdf
     *
     * @param inPath
     * @param outPath
     */
    public static Document doc2pdf(String inPath, String outPath) {
        try {
            File file = new File(outPath);
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath);
            doc.save(os, SaveFormat.PDF);
            os.close();
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
