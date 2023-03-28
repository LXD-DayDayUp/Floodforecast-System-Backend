package com.example;

import com.example.dao.CMQMapper;
import com.example.dao.GengzhangMapper;
import com.example.entity.*;
import com.example.service.*;
import com.example.xaj.HydrologicalModelHelper.ModelEvaluation;
import com.example.xaj.computer.computerxaj;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class Tests {
    @Autowired
    private ModelparService modelparService;

    @Autowired
    private GapsoparService gapsoparService;

    @Autowired
    private GaparService gaparService;

    @Autowired
    private PsoparService psoparService;

    @Resource
    private GengzhangMapper gengzhangMapper;
    @Resource
    private CMQMapper cmqMapper;
    @Autowired
    private RainfallService rainfallService;

    @Test
    void testGetAll() {
        Tests test = new Tests();
//        小论文率定了K、SM、CS、KSS、KG、KKSS、KKG
//        SM影响洪峰流量大小，SM越大洪峰流量越小
//       K影响模型产流量
//        河网蓄水消退系数CS影响洪峰
//        需要分析的参数有哪些？峰现时间、洪水总量、洪水峰值，
//


        ModelEvaluation me = new ModelEvaluation();
        Gapsopar gapsopar = gapsoparService.selectGAPSOParByID(1);
        Gapar Gapar = gaparService.selectGAParByID(1);
        Psopar Psopar = psoparService.selectPSOParByID(1);
        List<Gengzhang> gengzhangList = gengzhangMapper.selectPEByDate("20190101", "20191231");
        int n = gengzhangList.size();
        String[] Date = new String[n];
        double[] P = new double[n];
        double[] EI = new double[n];
        double[] MQ = new double[n];
//        double[] CQ = new double[n];
        for (int i = 0; i < n; i++) {
            Date[i] = String.valueOf(gengzhangList.get(i).getHyDate());
            P[i] = gengzhangList.get(i).getP();
            EI[i] = gengzhangList.get(i).getEI();
            MQ[i] = gengzhangList.get(i).getMQ();
        }
        computerxaj cxaj = new computerxaj();
        Gapsopar gapar = new Gapsopar(Gapar.getId(), Gapar.getBasin(), Gapar.getPopsize(),
                Gapar.getGeneration(), Gapar.getWum(), Gapar.getWlm(), Gapar.getWdm(),
                Gapar.getK(), Gapar.getC(), Gapar.getB(), Gapar.getImp(), Gapar.getSm(),
                Gapar.getCs(), Gapar.getEx(), Gapar.getKss(), Gapar.getKg(), Gapar.getKkss(),
                Gapar.getKkg());
        Gapsopar psopar = new Gapsopar(Psopar.getId(), Psopar.getBasin(), Psopar.getPopsize(),
                Psopar.getGeneration(), Psopar.getWum(), Psopar.getWlm(), Psopar.getWdm(),
                Psopar.getK(), Psopar.getC(), Psopar.getB(), Psopar.getImp(), Psopar.getSm(),
                Psopar.getCs(), Psopar.getEx(), Psopar.getKss(), Psopar.getKg(), Psopar.getKkss(),
                Psopar.getKkg());
        double[] GPCQ = cxaj.computeQ(gapsopar, P, EI);
        double[] GCQ = cxaj.computeQ(gapar, P, EI);
        double[] PCQ = cxaj.computeQ(psopar, P, EI);
//        确定性系数
        double GPNSE = ModelEvaluation.NashSutcliffeEfficiency(MQ, GPCQ);
        double GNSE = ModelEvaluation.NashSutcliffeEfficiency(MQ, GCQ);
        double PNSE = ModelEvaluation.NashSutcliffeEfficiency(MQ, PCQ);
//        实测径流深
        double MR = test.CaliR(MQ);
//        模拟径流深
        double GPR = test.CaliR(GPCQ);
        double GR = test.CaliR(GCQ);
        double PR = test.CaliR(PCQ);
//        径流深误差
        double GPRerror=MR-GPR;
        double GRerror=MR-GR;
        double PRerror=MR-PR;
//        实测峰值
        double MQmax = test.calimax(MQ);
//        预报洪峰值
        double GPCQmax = test.calimax(GPCQ);
        double GCQmax = test.calimax(GCQ);
        double PCQmax = test.calimax(PCQ);
        System.out.println("遗传算法+粒子群算法:"+ "实测径流深" + MR + "模拟径流深" + GPR+ "径流深误差" + GPRerror + "确定性系数" + GPNSE);
        System.out.println("遗传算法DC:" + "实测径流深" + MR + "模拟径流深" + GR+ "径流深误差" + GRerror + "确定性系数" + GNSE);
        System.out.println("粒子群算法DC:" + "实测径流深" + MR + "模拟径流深" + PR+ "径流深误差" + PRerror + "确定性系数" + PNSE);
    }

    public double CaliR(double[] Q) {
        double QSum = 0;
        double R = 0;
        double time = 24 * 60 * 60;
        for (int i = 0; i < Q.length; i++) {
            QSum += Q[i] * time;
        }
        R = QSum / (1000 * 15734);
        return R;
    }

    public double calimax(double[] Q) {
        double Qmax = 0;
        Arrays.sort(Q);
        Qmax = Q[Q.length - 1];
        return Qmax;
    }
}
