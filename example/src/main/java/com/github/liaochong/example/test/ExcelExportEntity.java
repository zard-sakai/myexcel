//package com.github.liaochong.example.test;
//
//import java.util.List;
//
//public class ExcelExportEntity extends ExcelBaseEntity implements Comparable<ExcelExportEntity> {
//
//    /**
//     * 如果是MAP导出,这个是map的key
//     */
//    private Object                  key;
//
//    private double                  width           = 10;
//
//    private double                  height          = 10;
//
//    /**
//     * 图片的类型,1是文件,2是数据库
//     */
//    private int                     exportImageType = 0;
//
//    /**
//     * 排序顺序
//     */
//    private int                     orderNum        = 0;
//
//    /**
//     * 是否支持换行
//     */
//    private boolean                 isWrap;
//
//    /**
//     * 是否需要合并
//     */
//    private boolean                 needMerge;
//    /**
//     * 单元格纵向合并
//     */
//    private boolean                 mergeVertical;
//    /**
//     * 合并依赖`
//     */
//    private int[]                   mergeRely;
//    /**
//     * 后缀
//     */
//    private String                  suffix;
//    /**
//     * 统计
//     */
//    private boolean                 isStatistics;
//
//    private String                   numFormat;
//    /**
//     *  是否隐藏列
//     */
//    private boolean                  isColumnHidden;
//    /**
//     * 枚举导出属性字段
//     */
//    private String                    enumExportField;
//
//    public boolean isColumnHidden() {
//        return isColumnHidden;
//    }
//
//    public void setColumnHidden(boolean columnHidden) {
//        isColumnHidden = columnHidden;
//    }
//
//    private List<ExcelExportEntity> list;
//
//    public ExcelExportEntity() {
//
//    }
//
//    public ExcelExportEntity(String name) {
//        super.name = name;
//    }
//
//    public ExcelExportEntity(String name, Object key) {
//        super.name = name;
//        this.key = key;
//    }
//
//    public ExcelExportEntity(String name, Object key, int width) {
//        super.name = name;
//        this.width = width;
//        this.key = key;
//    }
//
//    public int getExportImageType() {
//        return exportImageType;
//    }
//
//    public double getHeight() {
//        return height;
//    }
//
//    public Object getKey() {
//        return key;
//    }
//
//    public List<ExcelExportEntity> getList() {
//        return list;
//    }
//
//    public int[] getMergeRely() {
//        return mergeRely == null ? new int[0] : mergeRely;
//    }
//
//    public int getOrderNum() {
//        return orderNum;
//    }
//
//    public double getWidth() {
//        return width;
//    }
//
//    public boolean isMergeVertical() {
//        return mergeVertical;
//    }
//
//    public boolean isNeedMerge() {
//        return needMerge;
//    }
//
//    public boolean isWrap() {
//        return isWrap;
//    }
//
//    public void setExportImageType(int exportImageType) {
//        this.exportImageType = exportImageType;
//    }
//
//    public void setHeight(double height) {
//        this.height = height;
//    }
//
//    public void setKey(Object key) {
//        this.key = key;
//    }
//
//    public void setList(List<ExcelExportEntity> list) {
//        this.list = list;
//    }
//
//    public void setMergeRely(int[] mergeRely) {
//        this.mergeRely = mergeRely;
//    }
//
//    public void setMergeVertical(boolean mergeVertical) {
//        this.mergeVertical = mergeVertical;
//    }
//
//    public void setNeedMerge(boolean needMerge) {
//        this.needMerge = needMerge;
//    }
//
//    public void setOrderNum(int orderNum) {
//        this.orderNum = orderNum;
//    }
//
//    public void setWidth(double width) {
//        this.width = width;
//    }
//
//    public void setWrap(boolean isWrap) {
//        this.isWrap = isWrap;
//    }
//
//    public String getSuffix() {
//        return suffix;
//    }
//
//    public void setSuffix(String suffix) {
//        this.suffix = suffix;
//    }
//
//    public boolean isStatistics() {
//        return isStatistics;
//    }
//
//    public void setStatistics(boolean isStatistics) {
//        this.isStatistics = isStatistics;
//    }
//
//    public String getNumFormat() {
//        return numFormat;
//    }
//
//    public void setNumFormat(String numFormat) {
//        this.numFormat = numFormat;
//    }
//
//    public String getEnumExportField() {
//        return enumExportField;
//    }
//
//    public void setEnumExportField(String enumExportField) {
//        this.enumExportField = enumExportField;
//    }
//
//    @Override
//    public int compareTo(ExcelExportEntity prev) {
//        return this.getOrderNum() - prev.getOrderNum();
//    }
//
//    @Override
//    public int hashCode() {
//        final int prime = 31;
//        int result = 1;
//        result = prime * result + ((key == null) ? 0 : key.hashCode());
//        return result;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        ExcelExportEntity other = (ExcelExportEntity) obj;
//        if (key == null) {
//            if (other.key != null) {
//                return false;
//            }
//        } else if (!key.equals(other.key)) {
//            return false;
//        }
//        return true;
//    }
//}