package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.DispatchCommand;
import com.example.demo.entity.DispatchCommand;
import com.example.demo.mapper.DispatchCommandMapper;
import com.example.demo.service.IDispatchCommandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 调度命令管理表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Service
public class DispatchCommandServiceImpl extends ServiceImpl<DispatchCommandMapper, DispatchCommand> implements IDispatchCommandService {


    public List<DispatchCommand> findVideoAll() {

        return list();
    }
    public boolean  del(int id) {
        return removeById(id);
    }
    public boolean   up(DispatchCommand myWord) {
        QueryWrapper<DispatchCommand> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",myWord.getId());
        return  update(myWord,userQueryWrapper);
    }
    public boolean   add(DispatchCommand videos) {
        return save(videos);
    }
}
