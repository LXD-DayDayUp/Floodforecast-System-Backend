package com.example.generator;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    public static void main(String[] args) {
//        获取代码生成器对象
        AutoGenerator autoGenerator=new AutoGenerator();
//        设置数据库相关配置
        DataSourceConfig datasource=new DataSourceConfig();
        datasource.setDriverName("com.mysql.cj.jdbc.Driver");
        datasource.setUrl("jdbc:mysql://localhost:3306/db_authority_system?serverTimezone=UTC");
        datasource.setUsername("root");
        datasource.setPassword("Li123456");
        autoGenerator.setDataSource(datasource);


//       设置全局配置
        GlobalConfig globalConfig=new GlobalConfig();
//        设置代码输出路径
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java");
//        设置生成完毕是否打开生成代码所在的目录
        globalConfig.setOpen(false);
//        设置作者
        globalConfig.setAuthor("LXD");
//        设置是否覆盖原始生成的文件
        globalConfig.setFileOverride(true);
//        设置数据层接口
        globalConfig.setMapperName("%sMapper");
//        设置ID生成策略
        globalConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setServiceName("%sService");
        globalConfig.setBaseResultMap(true);    //映射文件中是否生成ResultMap配置
        globalConfig.setBaseColumnList(true);  //生成通用sql字段
        autoGenerator.setGlobalConfig(globalConfig);

//        设置包名相关配置
        PackageConfig packageInfo=new PackageConfig();
//        设置生成的包名，与代码所在位置不冲突
        packageInfo.setParent("com.baomidou");
//        设置实体类包名
        packageInfo.setEntity("entity");
//        设置数据层包名
        packageInfo.setMapper("dao");
        autoGenerator.setPackageInfo(packageInfo);

//        策略设置
        StrategyConfig strategyConfig=new StrategyConfig();
//        设置当前参与生成的表名，参数为可变参数
        strategyConfig.setInclude("sys_floodprogram");
//        设置数据库表的前缀名称，模块名=数据库表名-前缀名，例如：User=tbl_user-tbl_
        strategyConfig.setTablePrefix("sys_");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel); // 数据库字段下划线转驼峰命令策略
//        设置是否启用Rest风格
        strategyConfig.setRestControllerStyle(true);
//        设置乐观锁字段名
        strategyConfig.setVersionFieldName("version");
//        设置逻辑删除字段名
        strategyConfig.setLogicDeleteFieldName("delete");
//        设置是否启用lombok
        strategyConfig.setEntityLombokModel(true);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.execute();
    }
}
