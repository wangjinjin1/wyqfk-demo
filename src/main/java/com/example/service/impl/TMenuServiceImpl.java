package com.example.service.impl;

import com.example.po.TMenu;
import com.example.mapper.TMenuMapper;
import com.example.service.TMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wang
 * @since 2021-04-19
 */
@Service
public class TMenuServiceImpl extends ServiceImpl<TMenuMapper, TMenu> implements TMenuService {

    @Autowired
    private TMenuMapper tMenuMapper;

    @Override
    public List<TMenu> getMenusByRoleid(Integer roleid) {
        return tMenuMapper.getMenusByRoleid(roleid);
    }
}
