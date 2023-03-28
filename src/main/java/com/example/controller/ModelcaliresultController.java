package com.example.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.dao.FloodforecastMapper;
import com.example.dao.FlowMapper;
import com.example.dao.ModelcaliresultMapper;
import com.example.dao.RainfallMapper;
import com.example.entity.*;
import com.example.utils.Result;
import com.example.xaj.computer.computerxaj;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LXD
 * @since 2023-02-07
 */
@RestController
@RequestMapping("/api/modelcaliresult")
public class ModelcaliresultController {


    @Resource
    private ModelcaliresultMapper modelcaliresultMapper;

    @Resource
    private FloodforecastMapper floodforecastMapper;
    @Resource
    private RainfallMapper rainfallMapper;

    @Resource
    private FlowMapper flowMapper;

    computerxaj xaj = new computerxaj();

    /**
     * 根据流域断面查询当前用户所有方案
     */
    @PostMapping("/queryprogram")
    public Result queryprogram(@RequestBody Map<String, Object> params) {
        String username = params.get("username").toString();
        String flowsite = params.get("flowsite").toString();
        String basin = switch (flowsite) {
            case "增巴站", "多布水库入库", "工布江达站", "巴河桥站", "更张站", "泥曲站" -> "更张站";
            default -> null;
        };
        List<Modelcaliresult> modelcaliresults = modelcaliresultMapper.queryprogram(username, basin);
        return Result.ok(modelcaliresults);
    }

    /**
     * 根据用户名查询所有方案
     */
    @PostMapping("/queryallprogram")
    public Result queryallprogram(@RequestBody Map<String, Object> params) {
        String username = params.get("username").toString();
        List<Modelcaliresult> allprogram = modelcaliresultMapper.queryallprogram(username);
        return Result.ok(allprogram);
    }

    /**
     * 洪水预报界面
     * 获取实测流量和模拟流量
     */
    @PostMapping("/getMqAndCq")
    public Result getMqAndCq(@RequestBody Map<String, Object> params) {
        String username = params.get("username").toString();
        String flowsite = params.get("flowsite").toString();
        String starttime = params.get("starttime").toString();
        String endtime = params.get("endtime").toString();
        String resultname = params.get("resultname").toString();
        String basin = switch (flowsite) {
            case "增巴站", "工布江达站", "巴河桥站", "更张站", "泥曲站", "多布水库入库" -> "更张站";
            default -> null;
        };
        //        根据方案名resultname查询参数
        Modelcaliresult modelcaliresult = modelcaliresultMapper.queryprogramByResultname(username, resultname);
        //        根据流域查询降雨
        List<Rainfall> rainfallList = rainfallMapper.selectAllByDate(starttime, endtime, basin);
        //        根据流量站查询流量
        List<Flow> flowList = flowMapper.selectAllFlowByDate(starttime, endtime, flowsite);
        //        如果降雨序列和流量序列长度不同会报错
        //        设置返回值
        int n = rainfallList.size();
        String[] Date = new String[n];
        double[] CQ;
        double[] MQ = new double[n];
        double[] P = new double[n];
        double[] EI = new double[n];
        for (int i = 0; i < n; i++) {
            P[i] = rainfallList.get(i).getP();
            EI[i] = rainfallList.get(i).getEI();
            Date[i] = rainfallList.get(i).getDate();
            MQ[i] = flowList.get(i).getMQ();
        }
        Gapsopar par = new Gapsopar(modelcaliresult.getId(), modelcaliresult.getBasin(), modelcaliresult.getPopsize(),
                modelcaliresult.getGeneration(), modelcaliresult.getWum(), modelcaliresult.getWlm(), modelcaliresult.getWdm(),
                modelcaliresult.getK(), modelcaliresult.getC(), modelcaliresult.getB(), modelcaliresult.getImp(), modelcaliresult.getSm(),
                modelcaliresult.getCs(), modelcaliresult.getEx(), modelcaliresult.getKss(), modelcaliresult.getKg(),
                modelcaliresult.getKkss(), modelcaliresult.getKkg());
        CQ = xaj.computeQ(par, P, EI);
        for (int i = 0; i < CQ.length; i++) {
            Floodforecast result = floodforecastMapper.queryForeResult(LocalDate.parse(Date[i]), flowsite);
            if (result == null) {
                floodforecastMapper.storeForeResult(LocalDate.parse(Date[i]), flowsite, CQ[i]);
            } else {
                floodforecastMapper.updateForeResult(LocalDate.parse(Date[i]), flowsite, CQ[i]);
            }
        }

        Foreresult foreresult = new Foreresult(flowsite, P, Date, MQ, CQ);
        List<Foreresult> foreresultList = new ArrayList<>();
        foreresultList.add(foreresult);
        return Result.ok(foreresultList);
    }

    /**
     * 修改行数据
     */
    @PostMapping("/updaterowdata")
    public Result updaterowdata(@RequestBody Map<String, Object> params) {
        Modelcaliresult rowData = JSON.parseObject(JSON.toJSONString(params.get("rowData")), new TypeReference<Modelcaliresult>() {
        });
        modelcaliresultMapper.updaterowdata(rowData.getWum(), rowData.getWlm(), rowData.getWdm(), rowData.getK(), rowData.getSm(), rowData.getCs(), rowData.getKss(), rowData.getKg(), rowData.getKkss(), rowData.getKkg(), rowData.getResultname());
        return Result.ok().message("修改成功！");
    }

    /**
     * 删除行数据
     */
    @PostMapping("/deleterowdata")
    public Result deleterowdata(@RequestBody Map<String, Object> params) {
        String resultname=params.get("resultname").toString();
        modelcaliresultMapper.deleterowdata(resultname);
        return Result.ok().message("修改成功！");
    }
}

