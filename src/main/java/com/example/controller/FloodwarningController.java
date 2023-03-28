package com.example.controller;


import com.example.Hyanalogy.Hyanalogy;
import com.example.SendSms.SendSms;
import com.example.dao.FloodforecastMapper;
import com.example.dao.FlowMapper;
import com.example.dao.UserMapper;
import com.example.entity.Floodforecast;
import com.example.entity.Floodwarning;
import com.example.entity.Flow;
import com.example.entity.User;
import com.example.utils.Result;
import com.example.xaj.HydrologicalModelHelper.ModelEvaluation;
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
 * @since 2023-02-19
 */
@RestController
@RequestMapping("/api/floodwarning")
public class FloodwarningController {

    @Resource
    private FloodforecastMapper floodforecastMapper;

    @Resource
    private FlowMapper flowMapper;

    @Resource
    private UserMapper userMapper;
    //    创建水文比拟法对象
    Hyanalogy hyanalogy = new Hyanalogy();

    SendSms sendSms = new SendSms();

    ModelEvaluation modelEvaluation=new ModelEvaluation();

    @PostMapping("/queryFloodwarningResult")
    public Result queryFloodwarningResult(@RequestBody Map<String, Object> params) {
        String object = params.get("object").toString();
        String starttime = params.get("starttime").toString();
        String endtime = params.get("endtime").toString();
        String foresection = null;
        String leader = null;
        switch (object) {
            case "八一大桥":
                foresection = "多布水库入库";
                leader = "李四";
                break;
            case "多布水库":
                foresection = "更张站";
                leader = "王五";
                break;
        }
        List<Floodforecast> floodforecasts = floodforecastMapper.queryFloodFore(foresection, LocalDate.parse(starttime), LocalDate.parse(endtime));
        int n = floodforecasts.size();
        double[] duobu = new double[n];
        double[] bajiqu = new double[n];
        double[] juemuqu = new double[n];
        double[] kelaqu = new double[n];
        double[] niqu = new double[n];
        double[] bhq = new double[n];
        double[] bhqpre = new double[n];
        double[] gbjd = new double[n];
        double[] bayibridgeQ = new double[n];
        double[] bayibridgeL = new double[n];
        double[] preQ = new double[n];
        double[] prelevel = new double[n];
        double[] warlevel = new double[n];
        LocalDate[] Date = new LocalDate[n];
//        计算多布水电站出库流量
        for (int i = 0; i < n; i++) {
            Date[i] = floodforecasts.get(i).getDate();
            if (floodforecasts.get(i).getForeq() < 816) {
                duobu[i] = 816;
            } else if (floodforecasts.get(i).getForeq() > 816 && floodforecasts.get(i).getForeq() < 1700) {
                duobu[i] = 1761;
            } else if (floodforecasts.get(i).getForeq() > 1700 && floodforecasts.get(i).getForeq() < 2230) {
                duobu[i] = 2233;
            } else if (floodforecasts.get(i).getForeq() > 2230 && floodforecasts.get(i).getForeq() < 2670) {
                duobu[i] = 2806;
            } else if (floodforecasts.get(i).getForeq() > 2670 && floodforecasts.get(i).getForeq() < 2910) {
                duobu[i] = 2952;
            } else if (floodforecasts.get(i).getForeq() > 2910 && floodforecasts.get(i).getForeq() < 3130) {
                duobu[i] = 3178;
            } else if (floodforecasts.get(i).getForeq() > 3130 && floodforecasts.get(i).getForeq() < 3580) {
                duobu[i] = 3651;
            } else if (floodforecasts.get(i).getForeq() > 3580 && floodforecasts.get(i).getForeq() < 4800) {
                duobu[i] = 4871;
            }
        }
//        计算巴及曲流量、觉木曲流量
//        克拉曲水文站面积704km2,巴及曲面积306.6,觉木曲面积245,泥曲面积1605
        double KF = 704;
        double BF = 306.6;
        double JF = 245;
        double NF = 1605;
        int N = 2 / 3;
        List<Flow> niqulist = flowMapper.selectAllFlowByDate(starttime, endtime, "泥曲站");
        List<Flow> bhqlist = flowMapper.selectAllFlowByDate(starttime, endtime, "巴河桥站");
        List<Flow> gbjdlist = flowMapper.selectAllFlowByDate(starttime, endtime, "工布江达站");

        for (int i = 0; i < niqulist.size(); i++) {
            niqu[i] = niqulist.get(i).getMQ();
            bhq[i] = bhqlist.get(i).getMQ();
            gbjd[i] = gbjdlist.get(i).getMQ();
        }
        bhqpre=hyanalogy.hyanalogy(4229,6417,gbjd);
        double nase=modelEvaluation.NashSutcliffeEfficiency(bhq,bhqpre);
//        System.out.println(nase);
        kelaqu = hyanalogy.hyanalogy(KF, NF, niqu);
        bajiqu = hyanalogy.hyanalogy(BF, KF, kelaqu);
        juemuqu = hyanalogy.hyanalogy(JF, KF, kelaqu);
//        计算八一大桥流量、水位
        for (int i = 0; i < duobu.length; i++) {
            preQ[i] = hyanalogy.save(duobu[i] + bajiqu[i] + juemuqu[i]);
            prelevel[i]=hyanalogy.save(((preQ[i]/10)*16)+60);
            warlevel[i] = 3010.5;
        }
        List<Floodwarning> floodwarningList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Floodwarning floodwarning = new Floodwarning(object, Date[i], duobu[i], bajiqu[i], juemuqu[i], preQ[i], prelevel[i], warlevel[i], leader);
            floodwarningList.add(floodwarning);
        }
        return Result.ok(floodwarningList);
    }

    @PostMapping("/warninginfo")
    public Result warninginfo(@RequestBody Map<String, Object> params) {
        String object = params.get("object").toString();
        LocalDate Date = LocalDate.parse(params.get("date").toString());
        String preQ = params.get("preQ").toString();
        String prelevel = params.get("prelevel").toString();
        String warvalue = params.get("warvalue").toString();
        String leader = params.get("leader").toString();
        String warmessage = params.get("warmessage").toString();
//        查询安全责任人联系方式
        User user = userMapper.selectUserByUserrealname(leader);
        String phone = "86" + user.getPhone();
//        拼接日期
        String date = Date.getYear() + "年" + Date.getMonthValue() + "月" + Date.getDayOfMonth() + "日";
//{1}洪水流量将在{2}达到{3}立方米/秒，水位将达到{4}米，将超过警戒水位{5}米，请提前做好防汛抢险准备工作！
        sendSms.sendsms(phone, object, date, preQ, prelevel, warvalue);
        return Result.ok();
    }

}

