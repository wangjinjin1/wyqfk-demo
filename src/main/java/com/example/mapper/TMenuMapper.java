package com.example.mapper;

import com.example.po.TMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wang
 * @since 2021-04-19
 */
@Repository
public interface TMenuMapper extends BaseMapper<TMenu> {

    public List<TMenu> getMenusByRoleid(Integer roleid);
}
