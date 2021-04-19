package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.po.TDept;
import com.example.service.TDeptService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/tDept")
public class TDeptController {

    @Autowired
    private TDeptService tDeptService;

    @GetMapping("/getAll")
    public String getAll(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize){
        Page<TDept> page=new Page<>(pageNo,pageSize);
        tDeptService.page(page,null);
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
        TDept tDept=tDeptService.getById(id);
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        try {
            obj=objectMapper.writeValueAsString(tDept);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @PostMapping("/addDept")
    public String add(TDept tDept){
        boolean flag=tDeptService.save(tDept);
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

    @PostMapping("/updDept")
    public String upd(TDept tDept){
        boolean flag=tDeptService.saveOrUpdate(tDept);
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
        boolean flag=tDeptService.removeById(id);
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

