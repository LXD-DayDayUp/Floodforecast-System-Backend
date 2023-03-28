package com.example.Hyanalogy;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 水文比拟法
 */
public class Hyanalogy {


    public double[] hyanalogy(double FS, double FC, double[] QC) {
        Hyanalogy hyanalogy=new Hyanalogy();
        double n = 0.667;
        double[] qS = new double[QC.length];
        double[] QS = new double[QC.length];
        double AreaRatio = Math.pow(FS / FC, n);
        for (int i = 0; i < QC.length; i++) {
            qS[i] = QC[i] * AreaRatio;
            QS[i] = hyanalogy.save(qS[i]);
        }
        return QS;
    }

    public double save(double number){
        BigDecimal temp = new BigDecimal(number);
        double Number = temp.setScale(2, RoundingMode.HALF_UP).doubleValue();
        return Number;
    }
}
