package com.example.FuzzyOptimization;

import java.util.*;

public class FuzzyEvaluation {
    static FuzzyEvaluation fuzzyEvaluation=new FuzzyEvaluation();

    // 模糊综合评价函数
    public static double[] fuzzyEvaluation(double[][] decisionMatrix, double[] weight) {
        int n = decisionMatrix.length; // 决策个数
        int m = decisionMatrix[0].length; // 指标个数
        double[] fuzzyResult = new double[n]; // 存储模糊综合评价结果

        // 遍历每个决策
        for (int i = 0; i < n; i++) {
            double numerator = 0.0;
            double denominator = 0.0;
            // 遍历每个指标
            for (int j = 0; j < m; j++) {
                double wi = weight[j];
                double xij = decisionMatrix[i][j];
                numerator += wi * xij;
                denominator += wi;
            }
            fuzzyResult[i] = numerator / denominator;
        }
        return fuzzyResult;
    }

    // 隶属度函数
    public static double[][] largeIsBetter(double[][] decisionMatrix) {
        int h = decisionMatrix.length;
        int j = decisionMatrix[0].length;
        double[][] r = new double[h][j];
        double max = Integer.MIN_VALUE; // 最大值初始化为int类型的最小值
        double min = Integer.MAX_VALUE; // 最小值初始化为int类型的最大值
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                if (decisionMatrix[i][k] > max) {
                    max = decisionMatrix[i][k];
                } else if (decisionMatrix[i][k] < min) {
                    min = decisionMatrix[i][k];
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                r[i][k] = (decisionMatrix[i][k] - min) / (max - min);
            }
        }
        return r;
    }

    public static double[][] smallIsBetter(double[][] decisionMatrix) {
        int h = decisionMatrix.length;
        int j = decisionMatrix[0].length;
        double[][] r = new double[h][j];
        double max = Integer.MIN_VALUE; // 最大值初始化为int类型的最小值
        double min = Integer.MAX_VALUE; // 最小值初始化为int类型的最大值
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                if (decisionMatrix[i][k] > max) {
                    max = decisionMatrix[i][k];
                } else if (decisionMatrix[i][k] < min) {
                    min = decisionMatrix[i][k];
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                r[i][k] = 1 - (decisionMatrix[i][k] - min) / (max - min);
            }
        }
        return r;
    }

    public static double[][] middleIsBetter(double[][] decisionMatrix) {
        int h = decisionMatrix.length;
        int j = decisionMatrix[0].length;
        double[][] r = new double[h][j];
        double[][] temp1 = new double[h][j];
        double[][] temp2 = new double[h][j];
        double max = Integer.MIN_VALUE; // 最大值初始化为int类型的最小值
        double sum = 0.0;
        int count = 0;
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                sum += decisionMatrix[i][k];
                count++;
            }
        }
        double ave = sum / count;
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                if (decisionMatrix[i][k] > max) {
                    temp1[i][k] = decisionMatrix[i][k] - ave;
                    temp2[i][k] = decisionMatrix[i][k];
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int k = 0; k < j; k++) {
                r[i][k] = 1 - temp1[i][k] / temp2[i][k];
            }
        }
        return r;
    }
}
