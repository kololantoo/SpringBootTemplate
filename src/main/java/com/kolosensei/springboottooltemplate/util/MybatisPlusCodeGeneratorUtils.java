package com.kolosensei.springboottooltemplate.util;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhengyang
 * @version 1.0
 * @date 2020/9/1 15:57
 * @description mybatis plus代码生成器，快速生成ORM映射的相关类
 */
public class MybatisPlusCodeGeneratorUtils {
    //数据源配置
    private static DbType dbType = DbType.PHOENIX;                                                                //数据库类型
    private static String driverName = "com.mysql.jdbc.Driver";                                              //数据库驱动名
    private static String url = "jdbc:mysql://localhost:3306/data_convert_platform_db?characterEncoding=utf8&serverTimezone=UTC";    //数据库URL
    private static String userName = "root";                                                                    //用户名
    private static String password = "kolosensei@2020.com";                                                                //密码
    //全局配置
    private static String outputDir = System.getProperty("user.dir") + "/src/main/java";                          //生成文件输出目录
    private static String mapperName = "%sMapper";                                                              //mapper 命名方式
    private static String xmlName = "%sMapper";                                                                 //Mapper xml 命名方式
    private static String serviceName = "%sService";                                                            //service 命名方式
    private static String serviceImplName = "%sServiceImpl";                                                    //service impl 命名方式
    private static String controllerName = "%sController";                                                      //controller 命名方式
    private static String author = "kolosensei";                                                                //开发人员
    private static Boolean fileOverride = true;                                                                 //是否覆盖已有文件
    private static Boolean activeRecord = true;                                                                 //开启 ActiveRecord 模式
    private static Boolean enableCache = false;                                                                 //是否在xml中添加二级缓存配置
    private static Boolean baseResultMap = true;                                                                //开启 BaseResultMap
    private static Boolean baseColumnList = true;                                                               //开启 baseColumnList
    //策略配置
    private static NamingStrategy naming = NamingStrategy.underline_to_camel;                                    //数据库表映射到实体的命名策略
    private static Boolean entityLombokModel = true;                                                             //【实体】是否为lombok模型
    private static Boolean EntityTableFieldAnnotationEnable = true;                                              //是否生成实体时，生成字段注解
    private static Boolean EntityBooleanColumnRemoveIsPrefix = true;                                             //Boolean类型字段是否移除is前缀（默认 false）
    private static Boolean RestControllerStyle = true;                                                           //生成 @RestController 控制器
    private static String includeRegex = "saved_sql_tree";  //需要包含的表名，允许正则表达式（与exclude二选一配置）
    //    private static String excludeRegex = "";                                                                     //需要排除的表名，允许正则表达式
    //包配置
    private static String parent = "com.kolosensei.springboottooltemplate";                                                //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
    private static String controller = "controller";                                                             //Controller包名
    private static String service = "service";                                                                   //Service包名
    private static String serviceImpl = "service.impl";                                                          //Service Impl包名
    private static String entity = "model";                                                                      //Entity包名
    private static String mapper = "mapper";                                                                     //Mapper包名

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();
        //数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(dbType);
        dsc.setDriverName(driverName);
        dsc.setUrl(url);
        dsc.setUsername(userName);
        dsc.setPassword(password);
        dsc.setSchemaName("DIM");
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(fileOverride);
        gc.setActiveRecord(activeRecord);
        gc.setEnableCache(enableCache);
        gc.setBaseResultMap(baseResultMap);
        gc.setBaseColumnList(baseColumnList);
        gc.setMapperName(mapperName);
        gc.setXmlName(xmlName);
        gc.setServiceName(serviceName);
        gc.setServiceImplName(serviceImplName);
        gc.setControllerName(controllerName);
        gc.setAuthor(author);
        //策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(naming);
        strategy.setEntityLombokModel(entityLombokModel);
        strategy.setEntityTableFieldAnnotationEnable(EntityTableFieldAnnotationEnable);
        strategy.setEntityBooleanColumnRemoveIsPrefix(EntityBooleanColumnRemoveIsPrefix);
        strategy.setRestControllerStyle(RestControllerStyle);
//        strategy.setExclude(excludeRegex);
        strategy.setInclude(includeRegex);
        //包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(parent);
        pc.setController(controller);
        pc.setService(service);
        pc.setServiceImpl(serviceImpl);
        pc.setEntity(entity);
        pc.setMapper(mapper);

        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-rb");
                this.setMap(map);
            }
        };
        //xml生成路径
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return "src/main/resources/" + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 关闭默认 xml 生成，调整生成 至 根目录
        TemplateConfig tc = new TemplateConfig();
        tc.setXml(null);
        mpg.setDataSource(dsc);         //数据源配置
        mpg.setGlobalConfig(gc);        //全局配置
        mpg.setStrategy(strategy);      //生成策略配置
        mpg.setPackageInfo(pc);         //包配置
        mpg.setCfg(cfg);                //xml配置
        mpg.setTemplate(tc);            //
        // 执行生成
        mpg.execute();
    }
}

