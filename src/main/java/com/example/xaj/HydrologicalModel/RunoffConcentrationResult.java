package com.example.xaj.HydrologicalModel;

import com.example.xaj.HydrologicalModelHelper.ParameterValidation;

/**
 *汇流计算结果
 */
public class RunoffConcentrationResult {
    /**
     * 序列长度
     */
    public final int Length;

    /**
     * 地表径流序列
     */
    public double[] QRS;

    /**
     * 壤中流序列
     */
    public double[] QRSS;

    /**
     * 地下径流序列
     */
    public double[] QRG;

    /**
     * 总径流序列
     */
    public double[] Q;

    public double[] getQ() {
        return Q;
    }

    public void setQ(double[] q) {
        Q = q;
    }

    /**
     * @param n 序列长度
     */
    public RunoffConcentrationResult(int n) {
        QRS = new double[n];
        QRSS = new double[n];
        QRG = new double[n];
        Q = new double[n];
        Length = n;
    }

    /**
     * @param QRS  地表径流序列
     * @param QRSS 壤中流序列
     * @param QRG  地下径流序列
     * @param Q    总径流序列
     */
    public RunoffConcentrationResult(double[] QRS, double[] QRSS,
                                     double[] QRG, double[] Q) {
        this.Length = ParameterValidation.CheckIdenticalLength(QRS, QRSS, QRG, Q);

        this.QRS = QRS;
        this.QRSS = QRSS;
        this.QRG = QRG;
        this.Q = Q;
    }
}
