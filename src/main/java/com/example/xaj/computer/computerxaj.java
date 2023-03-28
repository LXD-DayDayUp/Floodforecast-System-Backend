package com.example.xaj.computer;

import com.example.entity.Gapsopar;
import com.example.xaj.HydrologicalModel.RunoffConcentrationResult;
import com.example.xaj.HydrologicalModel.XajModel;

public class computerxaj {
    private static RunoffConcentrationResult runoffConcentrationResult;    // 汇流计算结果
    public double[] computeQ(Gapsopar par, double[] P, double[] EI) {
        XajModel xaj = new XajModel();
        double wu0, wl0, wd0, s, qrss0, qrg0, dt, area;
        double wum = par.getWum();
        double wlm = par.getWlm();
        double wdm = par.getWdm();
        double k = par.getK();
        double c = par.getC();
        double b = par.getB();
        double imp = par.getImp();
        double sm = par.getSm();
        double ex = par.getEx();
        double kss = par.getKss();
        double kg = par.getKg();
        double kkss = par.getKkss();
        double kkg = par.getKkg();
        xaj.SetSoilWaterStorageParam(wum, wlm, wdm);
        xaj.SetEvapotranspirationParam(k, c);
        xaj.SetRunoffGenerationParam(b, imp);
        xaj.SetSourcePartitionParam(sm, ex, kss, kg);
        xaj.SetRunoffConcentrationParam(kkss, kkg, 15734);  //z控制面积15581平方千米
        xaj.ComputeRunoffGeneration(P, EI, 35, 70, 70);
        xaj.ComputeSourcePartition(10, 12);
        xaj.ComputeRunoffConcentration(40, 70, 12);
        runoffConcentrationResult = xaj.GetResult();
        double[] CQ=runoffConcentrationResult.getQ();
        return CQ;
    }
}
