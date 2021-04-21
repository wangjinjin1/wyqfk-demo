package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.po.TUser;
import com.example.service.TUserService;
import com.example.utils.ResponseObj;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
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
@RequestMapping("/tUser")
public class TUserController {

    @Autowired
    private TUserService tUserService;

    @GetMapping("/getAll")
    public String getAll(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content){
        Page<TUser> page=new Page<>(pageNo,pageSize);
        if(content!=null&&!"".equals(content)){
            QueryWrapper<TUser> wrapper=new QueryWrapper<>();
            wrapper.like("username",content);
            tUserService.page(page,wrapper);
        }else{
            tUserService.page(page,null);
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


    @PostMapping("/addUser")
    public String add(@RequestBody TUser tUser){
        boolean flag=tUserService.save(tUser);
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

    @GetMapping("/findById")
    public String findById(Integer id){
        TUser tUser=tUserService.getById(id);
        //ResponseObj responseObj=new ResponseObj(200,tUser);
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        try {
            obj=objectMapper.writeValueAsString(tUser);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @PostMapping("/updUser")
    public String upd(@RequestBody TUser tUser){
        boolean flag=tUserService.saveOrUpdate(tUser);
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
        boolean flag=tUserService.removeById(id);
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

    @GetMapping("/login")
    public String login(String username,String password){
        QueryWrapper<TUser> wrapper=new QueryWrapper<>();
        wrapper.eq("username",username).eq("password",password);
        List<TUser> user=tUserService.list(wrapper);
        if(user!=null){
            ObjectMapper objectMapper=new ObjectMapper();
            String obj=null;
            try {
                obj=objectMapper.writeValueAsString(user.get(0));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return obj;
        }else{
            return null;
        }
    }

}

