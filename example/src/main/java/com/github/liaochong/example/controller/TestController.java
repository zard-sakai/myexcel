package com.github.liaochong.example.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.handler.inter.IExcelExportServer;
import com.alibaba.fastjson.JSONObject;
import com.github.liaochong.example.test.ExcelColumnInfo;
import com.github.liaochong.myexcel.core.DefaultStreamExcelBuilder;
import com.github.liaochong.myexcel.utils.AttachmentExportUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: luozehuan
 * @date: 2022/01/24
 **/
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //    public static final String jsonColumn = "[{ \"columnIndex\": 1, \"dbFieldEnName\": \"column1\", \"excelColumnName\": \"商户编码\", \"type\": \"String\" }, { \"columnIndex\": 2, \"dbFieldEnName\": \"column2\", \"excelColumnName\": \"订单日期\", \"format\": \"yyyy-MM-dd\", \"type\": \"Date\" }, { \"columnIndex\": 3, \"dbFieldEnName\": \"column3\", \"excelColumnName\": \"清算日期\", \"format\": \"yyyy-MM-dd\", \"type\": \"Date\" }, { \"columnIndex\": 4, \"dbFieldEnName\": \"column4\", \"excelColumnName\": \"订单编号\", \"type\": \"String\" }, { \"columnIndex\": 5, \"dbFieldEnName\": \"column5\", \"excelColumnName\": \"订单行号\", \"type\": \"String\" }, { \"columnIndex\": 6, \"dbFieldEnName\": \"column6\", \"excelColumnName\": \"交易类型\", \"type\": \"String\" }, { \"columnIndex\": 7, \"dbFieldEnName\": \"column7\", \"excelColumnName\": \"订单金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 8, \"dbFieldEnName\": \"column8\", \"excelColumnName\": \"运费\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 9, \"dbFieldEnName\": \"column9\", \"excelColumnName\": \"佣金比例\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 10, \"dbFieldEnName\": \"column10\", \"excelColumnName\": \"佣金金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 11, \"dbFieldEnName\": \"column11\", \"excelColumnName\": \"平台红包金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 12, \"dbFieldEnName\": \"column12\", \"excelColumnName\": \"平台券金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 13, \"dbFieldEnName\": \"column13\", \"excelColumnName\": \"平台礼品卡金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 14, \"dbFieldEnName\": \"column14\", \"excelColumnName\": \"平台积分金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 15, \"dbFieldEnName\": \"column15\", \"excelColumnName\": \"平台满减金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 16, \"dbFieldEnName\": \"column16\", \"excelColumnName\": \"平台其它优惠金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 17, \"dbFieldEnName\": \"column17\", \"excelColumnName\": \"苏宁优惠金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 18, \"dbFieldEnName\": \"column18\", \"excelColumnName\": \"清算金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 19, \"dbFieldEnName\": \"column19\", \"excelColumnName\": \"备注\", \"type\": \"String\" } ]\n";
    public static final String jsonColumn = "[{ \"columnIndex\": 1, \"dbFieldEnName\": \"column1\", \"excelColumnName\": \"合作方\", \"type\": \"String\" }, { \"columnIndex\": 2, \"dbFieldEnName\": \"column2\", \"excelColumnName\": \"放款失败原因\", \"type\": \"String\" }, { \"columnIndex\": 3, \"dbFieldEnName\": \"column3\", \"excelColumnName\": \"失败笔数\", \"format\": \"#########.##\", \"type\": \"Double\" }, { \"columnIndex\": 4, \"dbFieldEnName\": \"column4\", \"excelColumnName\": \"失败金额\", \"format\": \"#########.##\", \"type\": \"Double\" } ]";

    public static final String sql = "select column1,column2,column3,column4 from test t";

    public static final int DEFAULT_MAX_ROW = 300000;

    private ResultSet executeSql(String sql) throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/test" ,
                "root" ,
                "baihuzi."
        );
        Statement statement = connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }

    @GetMapping("/myexcel/example/sxssfAndStream")
    public void sxssfAndStreamMode(HttpServletResponse response) throws Exception {
        // get column excel name
        List<ExcelColumnInfo> excelColumnInfoList = generateExcelColumnInfoList(jsonColumn);
        List<ExcelExportEntity> excelExportEntityList = generateExcelExportEntity(excelColumnInfoList);

        // get data,columns
        ResultSet resultSet = executeSql(sql);

        int columnCount = 0;
        if(Objects.nonNull(resultSet)){
            columnCount = resultSet.getMetaData().getColumnCount();
        }

        List<String> excelTitleList = getExcelTitleList(excelColumnInfoList);

        // generate xlsx
        DefaultStreamExcelBuilder<Map> streamExcelBuilder = DefaultStreamExcelBuilder
                .of(Map.class)
                .titles(excelTitleList)
//                .style("title->color:red","background-color:green","border-style:thin")
//                .waitQueueSize(100)
                .start();
        // 1. append data
        long startTime = System.currentTimeMillis();
        while (resultSet.next()){
            // 根据添加顺序构造有序Map，key:excelColumn对象 value:excelValue
            Map<String, Object> tmpMap = new LinkedHashMap<>();
            for (int i = 1; i <= columnCount; i++) { //数据放入map
                tmpMap.put(excelColumnInfoList.get(i - 1).getDbFieldEnName(), resultSet.getObject(i));
            }
            streamExcelBuilder.append(tmpMap);
        }
        // 3.generate workbook
        Workbook workbook = streamExcelBuilder.build();
        long endTime = System.currentTimeMillis();
        logger.info("sxssf+stream mode export excel: cost "+(endTime - startTime));
        // 4.write into response
        AttachmentExportUtil.export(workbook, "sxssf_stream", response);

    }

    @GetMapping("/myexcel/example/sxssf")
    public void sxssfMode(HttpServletResponse response) throws Exception {
        // get column excel name
        List<ExcelColumnInfo> excelColumnInfoList = generateExcelColumnInfoList(jsonColumn);
        List<ExcelExportEntity> excelExportEntityList = generateExcelExportEntity(excelColumnInfoList);

        // get data,columns
        ResultSet resultSet = executeSql(sql);

        int columnCount = 0;
        if(Objects.nonNull(resultSet)){
            columnCount = resultSet.getMetaData().getColumnCount();
        }

        List<String> excelTitleList = getExcelTitleList(excelColumnInfoList);

        // 1. append data
        List<Map<String, Object>> dataList = new ArrayList<>(); //rs遍历数据集合
        Workbook workbook = null;
        int workBookNum = 0;
        int resultNum = 0;
        long startTime = System.currentTimeMillis();
        while (resultSet.next()){
            resultNum++;
            // 根据添加顺序构造有序Map，key:excelColumn对象 value:excelValue
            Map<String, Object> tmpMap = new HashMap<String, Object>();
            for (int i = 1; i <= columnCount; i++) { //数据放入map
                tmpMap.put(excelColumnInfoList.get(i - 1).getDbFieldEnName(), resultSet.getObject(i));
            }
            dataList.add(tmpMap);
            if (dataList.size() == DEFAULT_MAX_ROW) {//每30w条创建一个工作簿
                //创建工作簿
                workBookNum++;
                workbook = ExcelExportUtil.exportExcel(dataList, ExcelType.XSSF);
                dataList.clear();
            }
        }
        if (workBookNum * DEFAULT_MAX_ROW < resultNum) {
            workbook = ExcelExportUtil.exportExcel(dataList, ExcelType.XSSF);
        }
        long endTime = System.currentTimeMillis();
        logger.info("sxssf mode export excel: cost "+(endTime - startTime));
        AttachmentExportUtil.export(workbook, "sxssf", response);
    }



    private List<String> getExcelTitleList(List<ExcelColumnInfo> excelColumnInfos){
        List<String> excelTitleList = new ArrayList<>();
        if(CollectionUtils.isEmpty(excelColumnInfos)){
            logger.info("标题行为空");
            return excelTitleList;
        }
        excelColumnInfos.stream().forEach(item -> {
            excelTitleList.add(item.getExcelColumnName());
        });
        return excelTitleList;
    }

    private static List<ExcelColumnInfo> generateExcelColumnInfoList(String excelColumnInfoJsonStr) {
        List<ExcelColumnInfo> list = null;
        if (StringUtils.isEmpty(excelColumnInfoJsonStr)) {
            return null;
        }
        list = JSONObject.parseArray(excelColumnInfoJsonStr, ExcelColumnInfo.class);
        //根据columnIndex排序
        list = list.stream().sorted((a, b) -> a.getColumnIndex() - b.getColumnIndex()).collect(Collectors.toList());
        return list;
    }

    /**
     * 生成excel表头和各列数据类型信息
     * @return
     */
    private static List<ExcelExportEntity> generateExcelExportEntity(List<ExcelColumnInfo> excelColumnInfoList) {
        if (CollectionUtils.isEmpty(excelColumnInfoList)) {
            return null;
        }
        List<ExcelExportEntity> entitys = new ArrayList<ExcelExportEntity>();
        for (ExcelColumnInfo excelColumnInfo : excelColumnInfoList) {
            ExcelExportEntity  entity = new ExcelExportEntity(excelColumnInfo.getExcelColumnName(), excelColumnInfo.getDbFieldEnName()); //new ExcelExportEntity("入学时间", "date");
            if (StringUtils.equalsIgnoreCase("Date", excelColumnInfo.getType())) {//如果是date类型，设置format
                if(StringUtils.isEmpty(excelColumnInfo.getFormat())) {
                    entity.setFormat("yyyy-MM-dd");//设置默认format
                } else {
                    entity.setFormat(excelColumnInfo.getFormat());
                }
            } else if (StringUtils.equalsIgnoreCase("Double", excelColumnInfo.getType())) {
                if(StringUtils.isEmpty(excelColumnInfo.getFormat())) {
                    entity.setNumFormat("###,###,###.##");//设置默认double类型format
                } else {
                    entity.setNumFormat(excelColumnInfo.getFormat());
                }
            }
            //end,其他数据类型无需设置默认数据类型
            entitys.add(entity);
        }
        return entitys;
    }
}
