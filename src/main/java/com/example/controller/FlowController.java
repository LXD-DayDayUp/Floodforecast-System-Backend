package com.example.controller;


import com.example.dao.FlowMapper;
import com.example.entity.Flow;
import com.example.utils.Result;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LXD
 * @since 2022-10-30
 */
@RestController
@RequestMapping("/api/flow")
@ResponseBody
public class FlowController {
    @Resource
    private FlowMapper flowMapper;

//    查询所有洪水数据
    @PostMapping("/selectAllFlow")
    public Result<List<Flow>> selectAllFlow(){
        List<Flow> flowList =flowMapper.selectAllFlow();
        return Result.ok(flowList);
    }

//    根据时间段查询洪水数据
    @PostMapping("/selectAllFlowByDate")
    public Result<List<Flow>> selectAllFlowByDate(@RequestBody Map<String,Object> params){
//        自己发送的参数和接收的参数是否一致,参考网址https://blog.csdn.net/qq_30631063/article/details/105610395
        List<Flow> DateFlowList=flowMapper.selectAllFlowByDate(params.get("starttime").toString(),params.get("endtime").toString(),params.get("sitevalue").toString());
        return Result.ok(DateFlowList);
    }
    //        修改洪水数据
    @PostMapping("/reviseflow")
    public Result<Flow> reviserainfall(@RequestBody Map<String,Object> params){
        Flow revisedflow =flowMapper.reviseflow(Double.parseDouble(params.get("mq").toString()),params.get("date").toString(),params.get("site").toString());
        return Result.ok(revisedflow);
    }
    //        删除洪水数据
    @PostMapping("/deleteflow")
    public Result<Flow> deleterainfall(@RequestBody Map<String,Object> params){
        flowMapper.deleteflow(params.get("date").toString(),params.get("site").toString());
        return Result.ok();
    }
    //        新增洪水数据
    @PostMapping("/addflow")
    public Result addflow(@RequestBody Map<String,Object> params){
        Flow flow = flowMapper.selectFlowByDate(params.get("date").toString(), params.get("site").toString());
        if (flow==null){
            flowMapper.addflow(params.get("site").toString(),params.get("date").toString(),Double.parseDouble(params.get("mq").toString()));
            return Result.ok().message("数据添加成功！");
        }else {
            return Result.ok().message("数据已存在！");
        }
    }
}

