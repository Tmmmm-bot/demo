package com.example.demo.controller;


import com.example.demo.dataview.ResultView;
import com.example.demo.entity.MaintenanceCapacity;
import com.example.demo.service.impl.MaintenanceCapacityServiceImpl;
import com.example.demo.service.impl.MaintenanceCapacityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 运用检修能力管理表 前端控制器
 * </p>
 *
 * @author jobob
 * @since 2025-06-05
 */
@RestController
@RequestMapping("/maintenance-capacity")
public class MaintenanceCapacityController {


    @Autowired
    MaintenanceCapacityServiceImpl service;
    @PostMapping("/findAll")
    public ResultView finAll(){

        try{
            List<MaintenanceCapacity> videoAll = service.findVideoAll();
            return   ResultView.success(videoAll);
        }catch (Exception e){
            return  ResultView.error("失败");
        }
    }
    @PostMapping("/add")
    public ResultView add(MaintenanceCapacity videos){

        try{
            service.add(videos);
            return   ResultView.success(videos);
        }catch (Exception e){
            return  ResultView.error("失败");
        }
    }
    @PostMapping("/del")
    public ResultView del(MaintenanceCapacity videos){

        try{
            service.del(videos.getId());
            return   ResultView.success(videos);
        }catch (Exception e){
            return  ResultView.error("失败");
        }
    }
    @PostMapping("/up")
    public ResultView up(MaintenanceCapacity videos){

        try{
            service.up(videos);
            return   ResultView.success(videos);
        }catch (Exception e){
            return  ResultView.error("失败");
        }
    }
}
