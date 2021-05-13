package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.po.TMenu;
import com.example.service.TMenuService;
import com.example.vo.MenuVo;
import com.example.vo.RoleMenu;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public Object getAll(@RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
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
//        ObjectMapper objectMapper=new ObjectMapper();
//        String obj=null;
//        try {
//            obj=objectMapper.writeValueAsString(map);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return map;
    }

    @GetMapping("/getById")
    public Object getById(Integer id){
        TMenu tMenu=tMenuService.getById(id);
//        ObjectMapper objectMapper=new ObjectMapper();
//        String obj=null;
//        try {
//            obj=objectMapper.writeValueAsString(tMenu);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
        return tMenu;
    }

    @PostMapping("/addMenu")
    public Object add(TMenu tMenu){
        boolean flag=tMenuService.save(tMenu);
        Map<String,Object> map=new HashMap<>();
//        ObjectMapper objectMapper=new ObjectMapper();
//        String obj=null;
        if(flag){
            map.put("staus",flag);
            map.put("状态","添加成功");
//            try {
//                obj=objectMapper.writeValueAsString(map);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
        }else{
            map.put("staus",flag);
            map.put("状态","添加失败");
//            try {
//                obj=objectMapper.writeValueAsString(map);
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
        }

        return map;
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
    public Object getMenus(Integer id){
        List<TMenu> list=tMenuService.getMenusByRoleid(id);
        List<TMenu> plist=tMenuService.list();
        List<MenuVo> parent=new ArrayList<>();
        plist.forEach(menu->{
            if(menu.getParentid()==0){
                parent.add(new MenuVo(menu.getId(),menu.getName(),menu.getIcon(),menu.getPath(),new ArrayList<MenuVo>()));
            }
        });
        parent.forEach(p->{
            list.forEach(c->{
                if(p.getId()==c.getParentid()){
                    p.getChildren().add(new MenuVo(c.getId(),c.getName(),c.getIcon(),c.getPath()));
                }
            });
        });
        for (int i = parent.size()-1; i >=0 ; i--) {
            if(parent.get(i).getChildren().size()<=0){
                parent.remove(i);
            }
        }
        return parent;
    }


    @GetMapping("/getMenuTree")
    public Object getMenuTree(){
        List<TMenu> list=tMenuService.list();
        List<RoleMenu> parentList=new ArrayList<>();
        //找到所有的父节点
        list.forEach(menu->{
            if(menu.getParentid()==0){
                parentList.add(new RoleMenu(menu.getId(),menu.getName(),new ArrayList<RoleMenu>()));
            }
        });
        parentList.forEach(p->{
            list.forEach(l->{
                if(p.getId()==l.getParentid()){
                    p.getChildren().add(new RoleMenu(l.getId(),l.getName(),new ArrayList<RoleMenu>()));
                }
            });
        });

        return parentList;
    }

}

