package com.example.controller;


import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.example.dao.GapsoparMapper;
import com.example.dao.RainfallMapper;
import com.example.entity.*;
import com.example.utils.Result;
import com.example.xaj.computer.computerxaj;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LXD
 * @since 2023-02-02
 */
@RestController
@RequestMapping("/api/gapsopar")
@ResponseBody
public class GapsoparController {

    @Resource
    private GapsoparMapper gapsoparMapper;

    @Resource
    private RainfallMapper rainfallMapper;

    computerxaj xaj = new computerxaj();


    //    查询率定后的参数
    @PostMapping("/queryAllParByFactor")
    public Result<List<Gapsopar>> queryAllParByFactor(@RequestBody Map<String, Object> params) {
        int popsize = Integer.parseInt(params.get("popsize").toString());
        int generation = Integer.parseInt(params.get("generation").toString());
        List<Gapsopar> parList = gapsoparMapper.queryAllParByFactor(popsize, generation);
        return Result.ok(parList);
    }

    //    根据率定后的参数计算模拟流量
    @PostMapping("/queryMqAndCq")
    public Result<List<CMQ>> queryMqAndCq(@RequestBody Map<String, Object> params) {
        String hydate = params.get("floodnumber").toString();
        String flowsite = params.get("flowsite").toString();
//        接收后端传递的JSON数组中的数组
        List<Gapsopar> modelpar = (List<Gapsopar>) params.get("modelpar");
        String starttime = null;
        String endtime = null;
        switch (hydate) {
            case "20160101" -> {
                starttime = "20160101";
                endtime = "20161231";
            }
            case "20170101" -> {
                starttime = "20170101";
                endtime = "20171231";
            }
            case "20180101" -> {
                starttime = "20180101";
                endtime = "20181231";
            }
            case "20190101" -> {
                starttime = "20190101";
                endtime = "20191231";
            }
            case "20200101" -> {
                starttime = "20200101";
                endtime = "20201231";
            }
        }
        List<CMQ> cmqList = new ArrayList<>();
//        for (int i=0;i<modelpar.size();i++) {
//            modelpar.class可输出该数据的类型，百度转换的方式
            JSONObject Par = JSON.parseObject(JSON.toJSONString(modelpar.get(0)));
            Gapsopar par = Par.toJavaObject(Gapsopar.class);
            String site = par.getBasin();
            List<Rainfall> rainfallList = rainfallMapper.selectAllByDate(starttime, endtime, site);
            List<Flow> queryflowresult = gapsoparMapper.queryMqAndCq(flowsite, starttime, endtime);
            int n = rainfallList.size();
            String[] Date = new String[n];
            double[] CQ;
            double[] MQ = new double[n];
            double[] P = new double[n];
            double[] EI = new double[n];
            for (int j = 0; j < n; j++) {
                P[j] = rainfallList.get(j).getP();
                EI[j] = rainfallList.get(j).getEI();
                Date[j] = rainfallList.get(j).getDate();
                MQ[j] = queryflowresult.get(j).getMQ();
            }
            CQ = xaj.computeQ(par, P, EI);
            CMQ cmq = new CMQ(site, Date, MQ, CQ);
            cmqList.add(cmq);
//        }
        return Result.ok(cmqList);
    }

    //    存储模型率定结果
    @PostMapping("/storeModelCaliResult")
    public Result storeModelCaliResult(@RequestBody Map<String, Object> params) {
        String resultName = params.get("resultName").toString();
        String username = params.get("username").toString();
        List<Caliresult> ModelCaliResult = (List<Caliresult>) params.get("resultTableData");
        JSONObject modelcaliresult = JSON.parseObject(JSON.toJSONString(ModelCaliResult.get(0)));
        Caliresult caliresult = modelcaliresult.toJavaObject(Caliresult.class);
        Caliresult queryresult = gapsoparMapper.queryResult(username, resultName, caliresult.getBasin(), caliresult.getPopsize(), caliresult.getGeneration());
        if (queryresult == null) {
            gapsoparMapper.addModelCaliResult(username, resultName, caliresult.getBasin(), caliresult.getPopsize(), caliresult.getGeneration(), caliresult.getWum(), caliresult.getWlm(), caliresult.getWdm(), caliresult.getK(), caliresult.getC(), caliresult.getB(), caliresult.getImp(), caliresult.getSm(), caliresult.getCs(), caliresult.getEx(), caliresult.getKss(), caliresult.getKg(), caliresult.getKkss(), caliresult.getKkg());
            return Result.ok("success").message("数据添加成功！");
        } else {
            return Result.exist().message("数据已存在！");
        }
    }

    //    修改行参数
    @PostMapping("/updaterowdata")
    public Result updaterowdata(@RequestBody Map<String, Object> params) {
        Gapsopar gapsopar = JSON.parseObject(JSON.toJSONString(params.get("rowData")), new TypeReference<Gapsopar>() {
        });
        gapsoparMapper.updaterowdata(gapsopar.getWum(), gapsopar.getWlm(), gapsopar.getWdm(), gapsopar.getK(), gapsopar.getC(), gapsopar.getB(), gapsopar.getImp(), gapsopar.getSm(), gapsopar.getCs(), gapsopar.getEx(), gapsopar.getKss(), gapsopar.getKg(), gapsopar.getKkss(), gapsopar.getKkg(), gapsopar.getId(), gapsopar.getBasin(), gapsopar.getPopsize(), gapsopar.getGeneration());
        return Result.ok();
    }
}

