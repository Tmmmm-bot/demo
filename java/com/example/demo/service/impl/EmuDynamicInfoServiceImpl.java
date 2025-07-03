package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.EmuDynamicInfo;
import com.example.demo.mapper.EmuDynamicInfoMapper;
import com.example.demo.service.IEmuDynamicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 动车组信息表 服务实现类
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@Service
public class EmuDynamicInfoServiceImpl extends ServiceImpl<EmuDynamicInfoMapper, EmuDynamicInfo> implements IEmuDynamicInfoService {

    public List<EmuDynamicInfo> findVideoAll() {

        return list();
    }
    public boolean  del(int id) {
        return removeById(id);
    }
    public boolean   up(EmuDynamicInfo myWord) {
        QueryWrapper<EmuDynamicInfo> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",myWord.getId());
        return  update(myWord,userQueryWrapper);
    }
    public boolean   add(EmuDynamicInfo videos) {
        return save(videos);
    }
}
