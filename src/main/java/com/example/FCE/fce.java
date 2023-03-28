package com.example.FCE;

import java.util.Arrays;

public class fce {
    public void fcemethod() {
//        设置权重
        double[][] W = new double[][]{
                {0.2, 0.3, 0.5}
        };
        double[][] G = {
                {1, 2, 3, 4},
                {3, 4, 5, 6},
                {4, 5, 6, 7}
        };
        double[][] Dod = new double[W.length][G[0].length];
        for (int i = 0; i < W.length; i++) {
            for (int j = 0; j < G[0].length; j++) {
                for (int k = 0; k < W.length; k++) {
                    Dod[i][j]+=W[i][k]*G[k][j];
                }
            }
        }
        System.out.println(Arrays.deepToString(Dod));
    }

    public static void main(String[] args) {
        fce fce = new fce();
        fce.fcemethod();
    }
}
