package com.example;

import com.example.dao.CMQMapper;
import com.example.dao.GengzhangMapper;
import com.example.entity.Gengzhang;
import com.example.entity.Modelpar;
import com.example.service.ModelparService;
import com.example.service.RainfallService;
import com.example.xaj.HydrologicalModelHelper.ModelEvaluation;
import com.example.xaj.computer.computerxaj;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class Springdemo2ApplicationTests {
    @Autowired
    private ModelparService modelparService;
    @Resource
    private GengzhangMapper gengzhangMapper;

    @Resource
    private CMQMapper cmqMapper;
    @Autowired
    private RainfallService rainfallService;

    @Test
    void testGetAll() {
//        小论文率定了K、SM、CS、KSS、KG、KKSS、KKG
//        SM影响洪峰流量大小，SM越大洪峰流量越小
//       K影响模型产流量
//        河网蓄水消退系数CS影响洪峰
//        需要分析的参数有哪些？峰现时间、洪水总量、洪水峰值，
//        for (int j = 1; j < 7; j++) {
        ModelEvaluation me = new ModelEvaluation();
        Modelpar mp = modelparService.selectParByID(0);
        List<Gengzhang> gengzhangList = gengzhangMapper.selectPEByDate("20160101", "20161231");
        int n = gengzhangList.size();
        String[] Date = new String[n];
        double[] P = new double[n];
        double[] EI = new double[n];
        double[] MQ = new double[n];
        double[] CQ = new double[n];
        for (int i = 0; i < n; i++) {
            Date[i] = String.valueOf(gengzhangList.get(i).getHyDate());
            P[i] = gengzhangList.get(i).getP();
            EI[i] = gengzhangList.get(i).getEI();
            MQ[i] = gengzhangList.get(i).getMQ();
        }
        computerxaj cxaj = new computerxaj();
        CQ = cxaj.computeQ(mp, P, EI);
        double F=0;
        double item=0;
        for (int i = 0; i < CQ.length; i++) {
            F+=CQ[i];
            if (item<CQ[i]){
                item=CQ[i];
            }
        }
        System.out.println(item);
        System.out.println(F);
        double NSE=me.NashSutcliffeEfficiency(MQ,CQ);
        System.out.println(NSE);
//        for (int z = 0; z < Date.length; z++) {
//            cmqMapper.update(CQ[z], Date[z]);
//        }
//        }
    }
}
