package com.sangoes.boot.uc;

import com.sangoes.boot.uc.modules.admin.mapper.SysMenuMapper;
import com.sangoes.boot.uc.modules.admin.vo.MenuVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * Copyright (c) 2018
 *
 * @author jerrychir
 * @date 2018/11/17 3:23 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MapperTest {

    @Autowired
    private SysMenuMapper menuMapper;

    @Test
    public void testMenuMapper(){
        List<MenuVo> admin = menuMapper.findMenuAuthByRoleCode("admin");
        System.out.println(admin);
    }
}
