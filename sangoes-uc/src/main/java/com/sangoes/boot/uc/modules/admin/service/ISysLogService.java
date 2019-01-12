package com.sangoes.boot.uc.modules.admin.service;

import com.sangoes.boot.common.service.IBaseService;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.uc.modules.admin.entity.SysLog;

import java.util.Map;

/**
 * <p>
 * 日志 服务类
 * </p>
 *
 * @author jerrychir
 * @since 2019-01-12
 */
public interface ISysLogService extends IBaseService<SysLog> {


    /**
     * 日志分页
     *
     * @param params
     * @return
     */
    PageData<SysLog> pageLog(Map<String, Object> params);
}
