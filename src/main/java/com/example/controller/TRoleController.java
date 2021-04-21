package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.po.TRole;
import com.example.po.TUser;
import com.example.service.TRoleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api
@RequestMapping("/tRole")
public class TRoleController {

    @Autowired
    private TRoleService tRoleService;

    @GetMapping("/getAll")
    @ApiOperation("所有角色的接口")
    public String getAll(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
                         @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                         @RequestParam(value = "content",required = false) String content){
        Page<TRole> page=new Page<>(pageNo,pageSize);
        if(content!=null&&!"".equals(content)){
            QueryWrapper<TRole> wrapper=new QueryWrapper<>();
            wrapper.like("role_name",content);
            tRoleService.page(page,wrapper);
        }else{
            tRoleService.page(page,null);
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

    @PostMapping("/addRole")
    @ApiOperation("添加角色的接口")
    public String add(@RequestBody TRole tRole){
        boolean flag=tRoleService.save(tRole);
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

    @PostMapping("/updRole")
    @ApiOperation("修改角色的接口")
    public String upd(@RequestBody TRole tRole){
        boolean flag=tRoleService.saveOrUpdate(tRole);
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
    @ApiOperation("删除角色的接口")
    public String delete(Integer id){
        boolean flag=tRoleService.removeById(id);
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

    @GetMapping("/getById")
    @ApiOperation("根据id获取角色的接口")
    public String getById(Integer id){
        TRole tRole=tRoleService.getById(id);
        ObjectMapper objectMapper=new ObjectMapper();
        String obj=null;
        try {
            obj=objectMapper.writeValueAsString(tRole);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }


}

