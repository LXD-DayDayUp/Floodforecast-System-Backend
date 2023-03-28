package com.example.controller;

import com.example.dao.RainfallMapper;
import com.example.entity.Rainfall;
import com.example.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

public class RainFallController {
    /**
     * <p>
     * 前端控制器
     * </p>
     *
     * @author LXD
     * @since 2022-10-27
     */
    @RestController
    @RequestMapping("/api/rainfall")
    @ResponseBody
    public static class RainfallController {
        @Resource
        private RainfallMapper rainfallMapper;
//        查询所有降雨数据
        @PostMapping("/selectAll")
        public Result<List<Rainfall>> selectAll() {
            List<Rainfall> rainfallList = rainfallMapper.selectAll();
            return Result.ok(rainfallList);
        }
//        根据时间段查询降雨数据
        @PostMapping("/selectAllByDate")
        public Result<List<Rainfall>> selectAllByDate(@RequestBody Map<String,Object> params){
            List<Rainfall> DaterainfallList =rainfallMapper.selectAllByDate(params.get("starttime").toString(),params.get("endtime").toString(),params.get("sitevalue").toString());
            return Result.ok(DaterainfallList);
        }
//        修改降雨数据
        @PostMapping("/reviserainfall")
        public Result<Rainfall> reviserainfall(@RequestBody Map<String,Object> params){
            Rainfall revisedrainfall =rainfallMapper.reviserainfall(Double.parseDouble(params.get("p").toString()),params.get("date").toString(),params.get("site").toString());
            return Result.ok(revisedrainfall);
        }
//        删除降雨数据
        @PostMapping("/deleterainfall")
        public Result deleterainfall(@RequestBody Map<String,Object> params){
            rainfallMapper.deleterainfall(params.get("date").toString(),params.get("site").toString());
            return Result.ok();
        }
//        新增降雨数据
        @PostMapping("/addrainfall")
        public Result addrainfall(@RequestBody Map<String,Object> params){
            Rainfall rainfall = rainfallMapper.selectByDate(params.get("date").toString(), params.get("site").toString());
            if (rainfall==null){
                rainfallMapper.addrainfall(Double.parseDouble(params.get("p").toString()),params.get("date").toString(),params.get("site").toString());
                return Result.ok().message("数据添加成功！");
            }else {
                return Result.ok().message("数据已存在！");
            }
        }
    }
}
