package com.github.liaochong.example.test;

import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: luozehuan
 * @date: 2022/01/20
 **/
public class Test {

    /**
     * [{ "columnIndex": 1, "dbFieldEnName": "column1", "excelColumnName": "合作方", "type": "String" }, { "columnIndex": 2, "dbFieldEnName": "column2", "excelColumnName": "放款失败原因", "type": "String" }, { "columnIndex": 3, "dbFieldEnName": "column3", "excelColumnName": "失败笔数", "format": "#########.##", "type": "Double" }, { "columnIndex": 4, "dbFieldEnName": "column4", "excelColumnName": "失败金额", "format": "#########.##", "type": "Double" } ]
     *
     * [{ "columnIndex": 1, "dbFieldEnName": "column1", "excelColumnName": "商户编码", "type": "String" }, { "columnIndex": 2, "dbFieldEnName": "column2", "excelColumnName": "订单日期", "format": "yyyy-MM-dd", "type": "Date" }, { "columnIndex": 3, "dbFieldEnName": "column3", "excelColumnName": "清算日期", "format": "yyyy-MM-dd", "type": "Date" }, { "columnIndex": 4, "dbFieldEnName": "column4", "excelColumnName": "订单编号", "type": "String" }, { "columnIndex": 5, "dbFieldEnName": "column5", "excelColumnName": "订单行号", "type": "String" }, { "columnIndex": 6, "dbFieldEnName": "column6", "excelColumnName": "交易类型", "type": "String" }, { "columnIndex": 7, "dbFieldEnName": "column7", "excelColumnName": "订单金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 8, "dbFieldEnName": "column8", "excelColumnName": "运费", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 9, "dbFieldEnName": "column9", "excelColumnName": "佣金比例", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 10, "dbFieldEnName": "column10", "excelColumnName": "佣金金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 11, "dbFieldEnName": "column11", "excelColumnName": "平台红包金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 12, "dbFieldEnName": "column12", "excelColumnName": "平台券金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 13, "dbFieldEnName": "column13", "excelColumnName": "平台礼品卡金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 14, "dbFieldEnName": "column14", "excelColumnName": "平台积分金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 15, "dbFieldEnName": "column15", "excelColumnName": "平台满减金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 16, "dbFieldEnName": "column16", "excelColumnName": "平台其它优惠金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 17, "dbFieldEnName": "column17", "excelColumnName": "苏宁优惠金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 18, "dbFieldEnName": "column18", "excelColumnName": "清算金额", "format": "###,###,###.##", "type": "Double" }, { "columnIndex": 19, "dbFieldEnName": "column19", "excelColumnName": "备注", "type": "String" } ]
     */

    public static final String jsonColumn = "[{ \"columnIndex\": 1, \"dbFieldEnName\": \"column1\", \"excelColumnName\": \"商户编码\", \"type\": \"String\" }, { \"columnIndex\": 2, \"dbFieldEnName\": \"column2\", \"excelColumnName\": \"订单日期\", \"format\": \"yyyy-MM-dd\", \"type\": \"Date\" }, { \"columnIndex\": 3, \"dbFieldEnName\": \"column3\", \"excelColumnName\": \"清算日期\", \"format\": \"yyyy-MM-dd\", \"type\": \"Date\" }, { \"columnIndex\": 4, \"dbFieldEnName\": \"column4\", \"excelColumnName\": \"订单编号\", \"type\": \"String\" }, { \"columnIndex\": 5, \"dbFieldEnName\": \"column5\", \"excelColumnName\": \"订单行号\", \"type\": \"String\" }, { \"columnIndex\": 6, \"dbFieldEnName\": \"column6\", \"excelColumnName\": \"交易类型\", \"type\": \"String\" }, { \"columnIndex\": 7, \"dbFieldEnName\": \"column7\", \"excelColumnName\": \"订单金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 8, \"dbFieldEnName\": \"column8\", \"excelColumnName\": \"运费\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 9, \"dbFieldEnName\": \"column9\", \"excelColumnName\": \"佣金比例\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 10, \"dbFieldEnName\": \"column10\", \"excelColumnName\": \"佣金金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 11, \"dbFieldEnName\": \"column11\", \"excelColumnName\": \"平台红包金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 12, \"dbFieldEnName\": \"column12\", \"excelColumnName\": \"平台券金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 13, \"dbFieldEnName\": \"column13\", \"excelColumnName\": \"平台礼品卡金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 14, \"dbFieldEnName\": \"column14\", \"excelColumnName\": \"平台积分金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 15, \"dbFieldEnName\": \"column15\", \"excelColumnName\": \"平台满减金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 16, \"dbFieldEnName\": \"column16\", \"excelColumnName\": \"平台其它优惠金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 17, \"dbFieldEnName\": \"column17\", \"excelColumnName\": \"苏宁优惠金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 18, \"dbFieldEnName\": \"column18\", \"excelColumnName\": \"清算金额\", \"format\": \"###,###,###.##\", \"type\": \"Double\" }, { \"columnIndex\": 19, \"dbFieldEnName\": \"column19\", \"excelColumnName\": \"备注\", \"type\": \"String\" } ]\n";

    public static void main(String[] args) {
        List<ExcelColumnInfo> excelColumnInfos = generateExcelColumnInfoList(jsonColumn);
        List<ExcelExportEntity> entitys = generateExcelExportEntity(excelColumnInfos);
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
