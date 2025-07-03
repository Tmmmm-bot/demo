package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.ShuntingOperation;
import com.example.demo.entity.ShuntingOperation;
import com.example.demo.mapper.ShuntingOperationMapper;
import com.example.demo.service.IShuntingOperationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 调车作业管理表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Service
public class ShuntingOperationServiceImpl extends ServiceImpl<ShuntingOperationMapper, ShuntingOperation> implements IShuntingOperationService {

    public List<ShuntingOperation> findVideoAll() {

        return list();
    }
    public boolean  del(int id) {
        return removeById(id);
    }
    public boolean   up(ShuntingOperation myWord) {
        QueryWrapper<ShuntingOperation> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",myWord.getId());
        return  update(myWord,userQueryWrapper);
    }
    public boolean   add(ShuntingOperation videos) {
        return save(videos);
    }
}
