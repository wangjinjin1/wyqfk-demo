package com.example.service;

import com.example.mapper.TMenuMapper;
import com.example.po.TMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wang
 * @since 2021-04-19
 */
public interface TMenuService extends IService<TMenu> {

    public List<TMenu> getMenusByRoleid(Integer roleid);
}
