package com.daimengshi.ddcms.pub.gen;

import com.daimengshi.ddcms.gencode.jf.JfEnjoy;
import com.jfinal.kit.Kv;
import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.DataDictionaryGenerator;
import com.jfinal.plugin.activerecord.generator.MetaBuilder;
import com.jfinal.plugin.activerecord.generator.TableMeta;
import io.jboot.codegen.CodeGenHelpler;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by zhoufeng on 2018/1/22.
 * html 代码生成
 */
public class CodeGenerator {
    private static CodeGenerator mJfGenerator = null;

    private static CodeGenerator me() {
        if (mJfGenerator == null)
            mJfGenerator = new CodeGenerator();
        return mJfGenerator;

    }

    public static final CodeGenerator me = new CodeGenerator();
    protected final JfEnjoy jfEngine = new JfEnjoy();

    protected Kv tablemetaMap = null;
    protected String packageBase = "com.daimengshi.ddcms.gencode.html";
    protected String srcFolder = "src";
    protected String viewFolder = "_CodeGen";
    protected String basePath = "";

    public CodeGenerator setPackageBase(String packageBase) {
        this.packageBase = packageBase;
        return this;
    }

    public CodeGenerator setBasePath(String basePath) {
        this.basePath = basePath;
        return this;
    }

    public CodeGenerator setSrcFolder(String srcFolder) {
        this.srcFolder = srcFolder;
        return this;
    }

    public CodeGenerator setViewFolder(String viewFolder) {
        this.viewFolder = viewFolder;
        return this;
    }

    protected class DataGenerator extends DataDictionaryGenerator {
        public DataGenerator(DataSource dataSource, String dataDictionaryOutputDir) {
            super(dataSource, dataDictionaryOutputDir);
        }

        public void rebuildColumnMetas(List<TableMeta> tableMetas) {
            super.rebuildColumnMetas(tableMetas);
        }
    }

    //把字符串首字母转换为大写
    public static String UpStr(String str) {
        return str.replaceFirst(str.substring(0, 1), str.substring(0, 1).toUpperCase());
    }

    private String toClassNameSmall(String className) {
        return new StringBuffer(className.substring(0, 1).toLowerCase()).append(className.substring(1)).toString();
    }

    private String toPackages(String className) {
        return new StringBuffer(packageBase).append(".").append(basePath)
                .append(".").append(className.toLowerCase()).toString();
    }

    public TableMeta getTableMeta(String tableName) {
        if (tablemetaMap == null) {

            DataSource dataSource = CodeGenHelpler.getDatasource();

            MetaBuilder metaBuilder = new MetaBuilder(dataSource);
            metaBuilder.setDialect(new MysqlDialect());
            //metaBuilder.addExcludedTable(_JFinalDemoGenerator.excludedTable);
            List<TableMeta> tableMetas = metaBuilder.build();
            new DataGenerator(dataSource, null).rebuildColumnMetas(tableMetas);

            if (tableMetas.size() == 0) {
                System.out.println("TableMeta 数量为 0，不生成任何文件");
                return null;
            }
            Kv kv = Kv.create();
            for (TableMeta tableMeta : tableMetas) {
                kv.set(tableMeta.name, tableMeta);
            }
            tablemetaMap = kv;
        }
        return (TableMeta) tablemetaMap.get(tableName);
    }

    /**
     * @param className
     * @param tableName
     */
    public CodeGenerator codeRender(String className, String tableName, String chinaName) {
        TableMeta tablemeta = getTableMeta(tableName);

        doHtml(className, tablemeta, chinaName);
        doContriller(className, tablemeta, chinaName);
        doPageSql(className, tablemeta, chinaName);

        return this;
    }

    //生成通用html页面
    public void doHtml(String className, TableMeta tablemeta, String chinaName) {
        String packages = toPackages(className);
        String classNameSmall = toClassNameSmall(className);
        String basePathUrl = basePath.replace('.', '/');

        Kv kv = Kv.by("tableMeta", tablemeta)
                .set("package", packages)
                .set("className", className)
                .set("chinaName", chinaName)
                .set("classNameSmall", classNameSmall)
                .set("basePath", basePathUrl);


        //查询
        StringBuilder listPath = new StringBuilder()
                .append(PathKit.getWebRootPath())
                .append("/")
                .append(viewFolder)
                .append("/")
                .append(basePathUrl)
                .append("/")
                .append(classNameSmall)
                .append("/")
                .append(classNameSmall)
                .append("List.html");

        jfEngine.render("/html/list.html", kv, listPath);

        //添加
        StringBuilder addPath = new StringBuilder()
                .append(PathKit.getWebRootPath())
                .append("/")
                .append(viewFolder)
                .append("/")
                .append(basePathUrl)
                .append("/")
                .append(classNameSmall)
                .append("/")
                .append(classNameSmall)
                .append("Add.html");

        jfEngine.render("/html/add.html", kv, addPath);

        //编辑
        StringBuilder editPath = new StringBuilder()
                .append(PathKit.getWebRootPath())
                .append("/")
                .append(viewFolder)
                .append("/")
                .append(basePathUrl)
                .append("/")
                .append(classNameSmall)
                .append("/")
                .append(classNameSmall)
                .append("Edit.html");
        jfEngine.render("/html/edit.html", kv, editPath);
    }

    //生成通用控制器
    public void doContriller(String className, TableMeta tablemeta, String chinaName) {
        String packages = toPackages(className);
        String classNameSmall = toClassNameSmall(className);
        String upClassName = UpStr(className);
        String basePathUrl = basePath.replace('.', '/');


        Kv kv = Kv.by("tableMeta", tablemeta)
                .set("package", packages)
                .set("className", className)
                .set("chinaName", chinaName)
                .set("upClassName", upClassName)
                .set("classNameSmall", classNameSmall)
                .set("basePath", basePathUrl);


        //查询
        StringBuilder contrillerPath = new StringBuilder()
                .append(PathKit.getWebRootPath())
                .append("/")
                .append(viewFolder)
                .append("/")
                .append(basePathUrl)
                .append("/")
                .append(classNameSmall)
                .append("/")
                .append("Admin")
                .append(upClassName)
                .append("Controller.java");
        jfEngine.render("/contriller/contriller.tp", kv, contrillerPath);


    }

    //生产通用分页
    public void doPageSql(String className, TableMeta tablemeta, String chinaName) {
        String packages = toPackages(className);
        String classNameSmall = toClassNameSmall(className);
        String upClassName = UpStr(className);
        String basePathUrl = basePath.replace('.', '/');


        Kv kv = Kv.by("tableMeta", tablemeta)
                .set("package", packages)
                .set("chinaName", chinaName)
                .set("className", className)
                .set("upClassName", upClassName)
                .set("classNameSmall", classNameSmall)
                .set("basePath", basePathUrl);


        //查询
        StringBuilder contrillerPath = new StringBuilder()
                .append(PathKit.getWebRootPath())
                .append("/")
                .append(viewFolder)
                .append("/")
                .append(basePathUrl)
                .append("/")
                .append(classNameSmall)
                .append("/")
                .append(upClassName)
                .append("Page.sql");
        jfEngine.render("/sql/page.sql", kv, contrillerPath);


    }


    public static void main(String[] args) {

        CodeGenerator.me.codeRender("log", "dms_log","日志");

    }

}
