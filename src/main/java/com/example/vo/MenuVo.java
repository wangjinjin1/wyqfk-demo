package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class MenuVo {

    private Integer id;

    private String name;

    private String icon;

    private String path;

    private List<MenuVo> children;

    public MenuVo(Integer id,String name,String icon,String path){
        this.id=id;
        this.name=name;
        this.icon=icon;
        this.path=path;
    }

}
