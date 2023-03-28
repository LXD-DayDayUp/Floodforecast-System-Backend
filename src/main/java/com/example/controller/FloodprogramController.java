package com.example.controller;


import com.example.FuzzyOptimization.FuzzyEvaluation;
import com.example.dao.FloodprogramMapper;
import com.example.entity.Floodprogram;
import com.example.entity.Fuzzyresult;
import com.example.utils.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author LXD
 * @since 2023-03-08
 */
@RestController
@RequestMapping("/api/floodprogram")
public class FloodprogramController {
    @Resource
    private FloodprogramMapper floodprogramMapper;

    //    查询所有方案
    @PostMapping("/queryallprogram")
    public Result<List<Floodprogram>> queryallprogram() {
        List<Floodprogram> programList = floodprogramMapper.queryAllprogram();
        return Result.ok(programList);
    }

    //    模糊预选
    @PostMapping("/fuzzyevalution")
    public Result<List<Fuzzyresult>> fuzzyevalution() {
        // 假设有3种不同的调度方案，每种方案有多个指标组成 水位、库容、出流量
        List<Floodprogram> programList = floodprogramMapper.queryAllprogram();
        int n = programList.size();
//        double[][] decisionMatrix = {
//                {0.6, 0.8, 0.2},
//                {0.3, 0.5, 0.6},
//                {0.8, 0.1, 0.7}
//        };
        double[][] decisionMatrix = new double[n][5];
        for (int i = 0; i < n; i++) {
            decisionMatrix[i][0] = programList.get(i).getReservoirlevel();
            decisionMatrix[i][1] = programList.get(i).getReservoircapacity();
            decisionMatrix[i][2] = programList.get(i).getReservoirq();
            decisionMatrix[i][3] = programList.get(i).getBayilevel();
            decisionMatrix[i][4] = programList.get(i).getBayilevel();
        }
        FuzzyEvaluation fuzzy = new FuzzyEvaluation();
        double[][] a = fuzzy.middleIsBetter(decisionMatrix);
        // 设定每个指标的权重
        double[] weight = {0.1, 0.2, 0.3, 0.2, 0.2};

        // 模糊综合评价
        double[] fuzzyResult = fuzzy.fuzzyEvaluation(a, weight);
        List<Fuzzyresult> Superioritylist=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Fuzzyresult fuzzyresult=new Fuzzyresult(programList.get(i).getProgramname(),fuzzyResult[i]);
            Superioritylist.add(fuzzyresult);
        }
        // 输出评价结果
        System.out.println("模糊综合评价结果为：" + Arrays.toString(fuzzyResult));
        return Result.ok(Superioritylist);
    }
}

