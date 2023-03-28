package com.example.xaj.HydrologicalModelHelper;

import java.util.stream.DoubleStream;

public class ModelEvaluation {
    /**
     * 计算模型预测结果的纳什效率系数
     *
     * @param obs 实际观测值序列
     * @param pre 模型预测值序列
     * @return 纳什效率系数
     */
    public static double NashSutcliffeEfficiency(double[] obs, double[] pre) {
        int n = ParameterValidation.CheckIdenticalLength(obs, pre);
        double avg = DoubleStream.of(obs).average().getAsDouble();
        double s1 = 0, s2 = 0;
        for (int i = 0; i < n; ++i) {
            s1 += Math.pow(obs[i] - pre[i], 2);
            s2 += Math.pow(obs[i] - avg, 2);
        }
        double NSE = 1 - s1 / s2;
        return NSE;

    }
}
