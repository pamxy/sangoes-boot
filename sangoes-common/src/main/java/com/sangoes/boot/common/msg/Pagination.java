package com.sangoes.boot.common.msg;

import lombok.Data;

/**
 * 分页
 */
@Data
public class Pagination {
    /**
     * 总条数
     */
    private long total;

    private long pageSize;

    private long currentPage;

    /**
     * @param total
     */
    public Pagination(long total) {
        this.total = total;
    }

    /**
     * @param total
     * @param pageSize
     * @param currentPage
     */
    public Pagination(long total, long pageSize, long currentPage) {
        this.total = total;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
    }
}
