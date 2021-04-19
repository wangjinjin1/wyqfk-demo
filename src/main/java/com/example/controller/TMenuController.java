package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.po.TMenu;
import com.example.service.TMenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/tMenu")
public class TMenuController {

    @Autowired
    private TMenuService tMenuService;

    @GetMapping("/getAll")
    public String getAll(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content){
        Page<TMenu> page=new Page<>(pageNo,pageSize);
        if(content!=null&&!"".equals(content)){
            QueryWrapper<TMenu> wrapper=new QueryWrapper<>();
            wrapper.like("name",content);
            tMenuService.page(page,wrapper);
        }else{
            tMenuService.page(page,null);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("list",page.getRecords());
        map.put("pageNo",page.getCurrent());
        map.put("pageSize",page.getSize());
        map.put("count",page.getTotal());
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        try {
            obj=objectMapper.writeValueAsString(map);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @GetMapping("/getById")
    public String getById(Integer id){
        TMenu tMenu=tMenuService.getById(id);
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        try {
            obj=objectMapper.writeValueAsString(tMenu);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @PostMapping("/addMenu")
    public String add(TMenu tMenu){
        boolean flag=tMenuService.save(tMenu);
        Map<String,Object> map=new HashMap<>();
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        if(flag){
            map.put("staus",flag);
            map.put("状态","添加成功");
            try {
                obj=objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            map.put("staus",flag);
            map.put("状态","添加失败");
            try {
                obj=objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    @PostMapping("/updMenu")
    public String upd(TMenu tMenu){
        boolean flag=tMenuService.saveOrUpdate(tMenu);
        Map<String,Object> map=new HashMap<>();
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        if(flag){
            map.put("staus",flag);
            map.put("状态","修改成功");
            try {
                obj=objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }else{
            map.put("staus",flag);
            map.put("状态","修改失败");
            try {
                obj=objectMapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

        return obj;
    }

    @GetMapping("/delete")
    public String delete(Integer id){
        boolean flag=tMenuService.removeById(id);
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

    @GetMapping("/getMenus")
    public String getMenus(Integer id){
        List<TMenu> list=tMenuService.getMenusByRoleid(id);
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        try {
            obj=objectMapper.writeValueAsString(list);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

}

