package com.hspedu.service;

import com.hspedu.entity.Monster;

import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 */
public interface MonsterService {
    //返回Monster列表
    public List<Monster> listMonster();

    //通过传入的名字，返回monster列表
    public List<Monster> findMonsterByName(String name);

    //处理妖怪登录
    public boolean login(String name);
}
