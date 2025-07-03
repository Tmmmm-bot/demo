package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.CrewManagement;
import com.example.demo.entity.CrewManagement;
import com.example.demo.mapper.CrewManagementMapper;
import com.example.demo.service.ICrewManagementService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 乘务组管理表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Service
public class CrewManagementServiceImpl extends ServiceImpl<CrewManagementMapper, CrewManagement> implements ICrewManagementService {

    public List<CrewManagement> findVideoAll() {

        return list();
    }
    public boolean  del(int id) {
        return removeById(id);
    }
    public boolean   up(CrewManagement myWord) {
        QueryWrapper<CrewManagement> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",myWord.getId());
        return  update(myWord,userQueryWrapper);
    }
    public boolean   add(CrewManagement videos) {
        return save(videos);
    }
}
