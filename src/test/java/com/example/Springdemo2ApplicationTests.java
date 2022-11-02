package com.example;

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
    @Autowired
    private RainfallService rainfallService;
    @Test
    void testGetAll() {
//        需要分析的参数有哪些？峰现时间、洪水总量、洪水峰值，纳什效率系数NSE
        Modelpar mp=modelparService.selectParByID(4);
        ModelEvaluation me=new ModelEvaluation();
        List<Gengzhang> gengzhangList=gengzhangMapper.selectPEByDate("20160101","20161231");
        int n=gengzhangList.size();
        double[] P=new double[n];
        double[] EI=new double[n];
        double[] MQ=new double[n];
        double[] CQ=new double[n];
        for (int i = 0; i < n; i++) {
            P[i]=gengzhangList.get(i).getP();
            EI[i]=gengzhangList.get(i).getEI();
            MQ[i]=gengzhangList.get(i).getMQ();
        }
        computerxaj cxaj=new computerxaj();
        CQ=cxaj.computeQ(mp,P,EI);
        //        洪水峰值
        double CQMAX=0;
        double MQMAX=0;
        for (int i = 0; i < CQ.length; i++) {
            if (CQMAX<CQ[i]){
                CQMAX=CQ[i];
            }
            if (MQMAX<MQ[i]){
                MQMAX=MQ[i];
            }
        }
        System.out.println("预报峰值为："+CQMAX);
        System.out.println("实测峰值为："+MQMAX);
//        峰现时间
        for (int i = 0; i < CQ.length; i++) {
            if (CQMAX==CQ[i]){
                System.out.println(i);
            }
            if (MQMAX==MQ[i]){
                System.out.println(i);
            }
        }
//        洪水总量
        long CQSUM=0;
        long MQSUM=0;
        for (int i = 0; i < CQ.length; i++) {
            CQSUM+=CQ[i]*24*60*60;
            MQSUM+=MQ[i]*24*60*60;
        }
        System.out.println("预报洪量为："+CQSUM);
        System.out.println("实测洪量为："+MQSUM);
//        纳什效率系数
        double NSE= ModelEvaluation.NashSutcliffeEfficiency(MQ,CQ);
        System.out.println("纳什效率系数"+NSE);
    }
}
