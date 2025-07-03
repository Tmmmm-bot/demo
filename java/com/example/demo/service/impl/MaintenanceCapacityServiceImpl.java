package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.MaintenanceCapacity;
import com.example.demo.entity.MaintenanceCapacity;
import com.example.demo.mapper.MaintenanceCapacityMapper;
import com.example.demo.service.IMaintenanceCapacityService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 运用检修能力管理表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Service
public class MaintenanceCapacityServiceImpl extends ServiceImpl<MaintenanceCapacityMapper, MaintenanceCapacity> implements IMaintenanceCapacityService {


    public List<MaintenanceCapacity> findVideoAll() {

        return list();
    }
    public boolean  del(int id) {
        return removeById(id);
    }
    public boolean   up(MaintenanceCapacity myWord) {
        QueryWrapper<MaintenanceCapacity> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",myWord.getId());
        return  update(myWord,userQueryWrapper);
    }
    public boolean   add(MaintenanceCapacity videos) {
        return save(videos);
    }
}
