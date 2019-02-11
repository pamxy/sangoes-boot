package com.sangoes.boot.uc.modules.admin.service.impl;

import com.sangoes.boot.common.service.impl.BaseServiceImpl;
import com.sangoes.boot.common.utils.page.PageData;
import com.sangoes.boot.common.utils.page.PageQuery;
import com.sangoes.boot.uc.modules.admin.entity.SysLog;
import com.sangoes.boot.uc.modules.admin.mapper.SysLogMapper;
import com.sangoes.boot.uc.modules.admin.service.ISysLogService;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 日志 服务实现类
 * </p>
 *
 * @author jerrychir
 * @since 2019-01-12
 */
@Service
public class SysLogServiceImpl extends BaseServiceImpl<SysLogMapper, SysLog> implements ISysLogService {

    /**
     * 日志分页
     *
     * @param params
     * @return
     */
    @Override
    public PageData<SysLog> pageLog(Map<String, Object> params) {
        // 获取参数
        Object title = params.get("title");
        Object method = params.get("method");
        Object creator = params.get("creator");
        return this.selectPage(new PageQuery(params));
    }
}
