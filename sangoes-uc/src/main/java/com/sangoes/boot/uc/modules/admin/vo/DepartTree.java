package com.sangoes.boot.uc.modules.admin.vo;

import com.sangoes.boot.common.vo.TreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Copyright (c) sangoes 2018
 * https://github.com/sangoes
 *
 * @author jerrychir
 * @date 2018 2018/12/23 1:03 PM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DepartTree extends TreeNode {

    private String name;

    private String des;
}
