package upgrade.pojo;


import java.lang.reflect.Method;
import java.util.List;

public class ExcelBaseEntity {
    /**
     * 对应name
     */
    protected String     name;
    /**
     * 对应groupName
     */
    protected String     groupName;
    /**
     * 对应type
     */
    private int          type = BaseEntityTypeConstants.STRING_TYPE;
    /**
     * 数据库格式
     */
    private String       databaseFormat;
    /**
     * 导出日期格式
     */
    private String       format;
    /**
     * 导出日期格式
     */
    private String[]     replace;
    /**
     * 字典名称
     */
    private String       dict;
    /**
     * set/get方法
     */
    private Method method;
    /**
     * 这个是不是超链接,如果是需要实现接口返回对象
     */
    private boolean     hyperlink;
    /**
     * 固定的列
     */
    private Integer      fixedIndex;

    private List<Method> methods;

    public String getDatabaseFormat() {
        return databaseFormat;
    }

    public String getFormat() {
        return format;
    }

    public Method getMethod() {
        return method;
    }

    public List<Method> getMethods() {
        return methods;
    }

    public String getName() {
        return name;
    }

    public String[] getReplace() {
        return replace;
    }

    public int getType() {
        return type;
    }

    public void setDatabaseFormat(String databaseFormat) {
        this.databaseFormat = databaseFormat;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public void setMethods(List<Method> methods) {
        this.methods = methods;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setReplace(String[] replace) {
        this.replace = replace;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isHyperlink() {
        return hyperlink;
    }


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setHyperlink(boolean hyperlink) {
        this.hyperlink = hyperlink;
    }

    public int getFixedIndex() {
        return fixedIndex;
    }

    public void setFixedIndex(int fixedIndex) {
        this.fixedIndex = fixedIndex;
    }

    public String getDict() {
        return dict;
    }

    public void setDict(String dict) {
        this.dict = dict;
    }

    public void setFixedIndex(Integer fixedIndex) {
        this.fixedIndex = fixedIndex;
    }


}