package com.example.controller;


import com.example.po.TRoleMenu;
import com.example.service.TRoleMenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wang
 * @since 2021-04-19
 */
@RestController
@RequestMapping("/tRoleMenu")
public class TRoleMenuController {

    @Autowired
    private TRoleMenuService tRoleMenuService;

    @PostMapping("/addBatch")
    public String addBatch(){
//        Collection<TRoleMenu> collection=
//        tRoleMenuService.saveBatch()
        return "";
    }

    @GetMapping("/delBatch")
    public String delBatch(Integer roleid){
        boolean flag=tRoleMenuService.removeById(roleid);
        Map<String,Object> map=new HashMap<>();
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        if(flag){
            map.put("staus",flag);
            map.put("状态","删除成功");
            try {
                obj=objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            map.put("staus",flag);
            map.put("状态","删除失败");
            try {
                obj=objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }
}

