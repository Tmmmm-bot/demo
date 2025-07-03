package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.TemperaturePlan;
import com.example.demo.entity.TemperaturePlan;
import com.example.demo.mapper.TemperaturePlanMapper;
import com.example.demo.service.ITemperaturePlanService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 调度计划管理表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Service
public class TemperaturePlanServiceImpl extends ServiceImpl<TemperaturePlanMapper, TemperaturePlan> implements ITemperaturePlanService {


    public List<TemperaturePlan> findVideoAll() {

        return list();
    }
    public boolean  del(int id) {
        return removeById(id);
    }
    public boolean   up(TemperaturePlan myWord) {
        QueryWrapper<TemperaturePlan> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",myWord.getId());
        return  update(myWord,userQueryWrapper);
    }
    public boolean   add(TemperaturePlan videos) {
        return save(videos);
    }
}
