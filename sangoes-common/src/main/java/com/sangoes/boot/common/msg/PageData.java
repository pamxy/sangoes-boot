package com.sangoes.boot.common.msg;

import java.util.Collections;
import java.util.List;

import lombok.Data;

/**
 * 封装咯 总条数 分页行
 *
 * @param <T> the type parameter
 * @author jc
 */
@Data
public class PageData<T> {
    /**
     * The Total.
     */

    private Pagination pagination;
    /**
     * The list.
     */
    private List<T> list = Collections.emptyList();

    /**
     * 无参构造函数
     */
    public PageData() {
    }

    /**
     * Instantiates a new Table data.
     *
     * @param pagination the pagination
     * @param list       the list
     */
    public PageData(Pagination pagination, List<T> list) {
        this.pagination = pagination;
        this.list = list;
    }

}
