package com.example.demo.controller;

import com.example.demo.dataview.ResultView;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/uplode")
public class UploadController {
    Logger log = Logger.getLogger("UploadController");

    @Value("${getimg.pic}")
    String getimg;

    @RequestMapping("/imgs")
    public ResultView upImgs(@RequestParam("file") MultipartFile files[]){
        try {

            if (null==files||files.length==0){
                return ResultView.error("文件不许为空！");
            }
            List<String> urlfiles = new ArrayList<>();
            for (int i=0;i<files.length;i++){
                String fileName = System.currentTimeMillis() +".png";
                String destFileName = System.getProperty("user.dir") + "/images/"+fileName ;
                File destFile = new File(destFileName);
                //5.把浏览器上传的文件复制到希望的位置
                files[i].transferTo(destFile);
                urlfiles.add(getimg+fileName);
            }

            return ResultView.success(urlfiles);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        }

    }

    @RequestMapping("/imgs2")
    public ResultView upImgs2(@RequestParam("file") MultipartFile files[]){
        try {

            if (null==files||files.length==0){
                return ResultView.error("文件不许为空！");
            }

            Map<String,String> map=new HashMap<>();
            List<String> urlfiles = new ArrayList<>();
            for (int i=0;i<files.length;i++){
                String fileName = System.currentTimeMillis() +".png";
                String destFileName = System.getProperty("user.dir") + "/images/"+fileName ;
                File destFile = new File(destFileName);
                //5.把浏览器上传的文件复制到希望的位置
                files[i].transferTo(destFile);
                urlfiles.add(getimg+fileName);

                map.put("src","http://127.0.0.1:8082/demo"+getimg+fileName);
                map.put("titel","http://127.0.0.1:8082/demo"+getimg+fileName);

            }

            return ResultView.success(map);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        }

    }
    @RequestMapping("/txts")
    public ResultView upTxt(@RequestParam("file") MultipartFile files[]){
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了

            String suffix = files[0].getOriginalFilename().substring( files[0].getOriginalFilename().lastIndexOf("."));

            if (!suffix.contains("txt")){
                return ResultView.error("文件类型错误！");
            }
            String fileName = System.currentTimeMillis() +suffix;
            String destFileName = System.getProperty("user.dir") + "/txts/"+fileName ;
            File destFile = new File(destFileName);
            //5.把浏览器上传的文件复制到希望的位置
            files[0].transferTo(destFile);
            return ResultView.success(getimg+fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        }

    }

    @RequestMapping("/video")
    public ResultView upVideo(@RequestParam("file") MultipartFile files[]){
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了

            List<String> list= new ArrayList<>();
            for(int i=0;i<files.length;i++){
                String suffix = files[i].getOriginalFilename().substring( files[i].getOriginalFilename().lastIndexOf("."));
                String fileName = System.currentTimeMillis() +suffix;
                String destFileName = System.getProperty("user.dir") + "/videos/"+fileName ;
                File destFile = new File(destFileName);
                files[i].transferTo(destFile);
                list.add(getimg+fileName);
            }
            return ResultView.success(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            return ResultView.error(e.getMessage());
        }

    }



}
