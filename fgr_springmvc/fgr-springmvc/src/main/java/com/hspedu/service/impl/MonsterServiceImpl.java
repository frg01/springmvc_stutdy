package com.hspedu.service.impl;

import com.hspedu.entity.Monster;
import com.hspedu.fgrspringmvc.annotation.Service;
import com.hspedu.service.MonsterService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: guorui fu
 * @versiion: 1.0
 * @Service 注入容器
 */
@Service
public class MonsterServiceImpl implements MonsterService {
    @Override
    public List<Monster> listMonster() {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100,"牛魔王","芭蕉扇",400));
        monsters.add(new Monster(200,"老猫妖","上树",200));
        return monsters;
    }

    @Override
    public List<Monster> findMonsterByName(String name) {
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100,"牛魔王","芭蕉扇",400));
        monsters.add(new Monster(200,"老猫妖","上树",200));
        monsters.add(new Monster(200,"蜘蛛精","吐丝",150));

        //创建集合返回查询到的monster集合

        List<Monster> findMonsters
                = new ArrayList<>();
        //遍历monsters 返回满足条件的
        for (Monster monster : monsters) {
            if (monster.getName().contains((name))){
                findMonsters.add(monster);
            }
        }
        return findMonsters;
    }

    @Override
    public boolean login(String name) {
        if ("白骨精".equals(name)){
            return true;
        }
        return false;
    }
}
