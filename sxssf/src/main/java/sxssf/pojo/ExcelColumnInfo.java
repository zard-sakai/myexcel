package sxssf.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelColumnInfo implements Serializable {

    /** 对应数据库sql字段下标，从0开始,用于排序 */
    private int columnIndex;

    /** sql英文字段名 */
    private String dbFieldEnName;

    /** 代表java数据类型，有double，string，date,int,null，
     * 一搬标记为null，
     * 只有double和date类型进行特殊处理
     */
    private String type;

    /** 格式化format,date类型需要格式化，double类型防止科学记数法*/
    private String format;

    /**
     * excel列名
     */
    private String excelColumnName;
}
