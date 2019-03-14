package com.nf147.oukele;

import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * 自动生成SSM框架需要的东西
 *
 * @author OUKELE
 * @create 2019-03-13 10:57
 */

public class Generate {

    // 当前项目路径
    static String projectPath = System.getProperty("user.dir");
    //包名
    //static String Package ="com.oukele.demo";
    //数据库驱动 只支持 mariadb数据库的)
    static String drive = "org.mariadb.jdbc.Driver";
    //URL
    static String url = "jdbc:mariadb://localhost:3306/test";
    //数据库账号
    static String user = "oukele";
    //数据库密码
    static String password = "oukele";
    //组织名 用于自动生成 dao 、mapper_xml、entity
    static String Package = "com.nf147.oukele";
    //项目名
    static String projectName = "demo1";

    public static void main(String[] args) {
        Use();
    }

    //命令式 调用
    private static void Use() {
        System.out.println("\t\t请输入命令\t\t\n" +
                "输入 1 则生成 pom.xml 文件\n" +
                "输入 2 则生成 generatorConfig.xml 文件\n" +
                "输入 3 则生成 Spring、SpringMVC等 相关xml 文件" +
                "输入 4 则生成 service、serviceIpml、controller\n" +
                "输入 5 则 删除自动生成文件，避免污染项目\n" +
                "==================================================");
        int number = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("请输入你的命令：");
        number = in.nextInt();
        switch (number) {
            case 1:
                generate_pomXML();
                break;
            case 2:
                generate_generatorConfig();
                break;
            case 3:
                start_1();
                break;
            case 4:
                start();
                break;
            case 5:
                del();
                del_0();
                System.out.println(" 自动生成文件已删除......");
                System.out.println(" 请刷新 maven项目，然后尽情的使用吧.....");
                break;
            default:
                System.out.println("没有这个命令.....");
                break;
        }

    }

    //生成 自动配置 pom.xml 文件
    public static void generate_pomXML() {

        File f = new File(projectPath + File.separator + "/pom.xml");
        try {
            OutputStream out = new FileOutputStream(f);
            StringBuffer sbf = new StringBuffer();
            sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                    "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                    "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd\">\n" +
                    "    <modelVersion>4.0.0</modelVersion>\n" +
                    "\n" +
                    "    <!--组织名-->\n" +
                    "    <groupId>" + Package + "</groupId>\n" +
                    "    <!--项目名-->\n" +
                    "    <artifactId>" + projectName + "</artifactId>\n" +
                    "    <!--版本信息-->\n" +
                    "    <version>1.0</version>\n" +
                    "    <!--项目类型-->\n" +
                    "    <packaging>war</packaging>\n" +
                    "\n" +
                    "\n" +
                    "    <!--版本信息管理-->\n" +
                    "    <properties>\n" +
                    "        <spring.version>5.1.2.RELEASE</spring.version>\n" +
                    "        <springmvc.version>5.1.2.RELEASE</springmvc.version>\n" +
                    "        <!--项目构建编码-->\n" +
                    "        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>\n" +
                    "        <!-- maven 编译资源 版本 -->\n" +
                    "        <maven.compiler.source>1.8</maven.compiler.source>\n" +
                    "        <!-- maven 编译目标版本  -->\n" +
                    "        <maven.compiler.target>1.8</maven.compiler.target>\n" +
                    "        <!-- maven 编译插件版本 -->\n" +
                    "        <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>\n" +
                    "    </properties>\n" +
                    "\n" +
                    "    <!--jar包的依赖-->\n" +
                    "    <dependencies>\n" +
                    "        <!--Spring 的依赖-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.springframework</groupId>\n" +
                    "            <artifactId>spring-web</artifactId>\n" +
                    "            <version>${spring.version}</version>\n" +
                    "        </dependency>\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.springframework</groupId>\n" +
                    "            <artifactId>spring-aop</artifactId>\n" +
                    "            <version>${spring.version}</version>\n" +
                    "        </dependency>\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.springframework</groupId>\n" +
                    "            <artifactId>spring-jdbc</artifactId>\n" +
                    "            <version>${spring.version}</version>\n" +
                    "        </dependency>\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.springframework</groupId>\n" +
                    "            <artifactId>spring-test</artifactId>\n" +
                    "            <version>${spring.version}</version>\n" +
                    "            <scope>test</scope>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <!--Spring MVC 的依赖-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.springframework</groupId>\n" +
                    "            <artifactId>spring-webmvc</artifactId>\n" +
                    "            <version>${springmvc.version}</version>\n" +
                    "        </dependency>\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.springframework</groupId>\n" +
                    "            <artifactId>spring-web</artifactId>\n" +
                    "            <version>${springmvc.version}</version>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <!--mybatis 的依赖-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.mybatis</groupId>\n" +
                    "            <artifactId>mybatis</artifactId>\n" +
                    "            <version>3.4.6</version>\n" +
                    "        </dependency>\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.mybatis</groupId>\n" +
                    "            <artifactId>mybatis-spring</artifactId>\n" +
                    "            <version>1.3.2</version>\n" +
                    "        </dependency>\n" +
                    "        <!--mybatis 分页-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>com.github.pagehelper</groupId>\n" +
                    "            <artifactId>pagehelper</artifactId>\n" +
                    "            <version>5.1.7</version>\n" +
                    "        </dependency>\n" +
                    "        <!--c3p0 连接池 -->\n" +
                    "        <dependency>\n" +
                    "            <groupId>com.mchange</groupId>\n" +
                    "            <artifactId>c3p0</artifactId>\n" +
                    "            <version>0.9.5.2</version>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <!--数据库驱动-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>org.mariadb.jdbc</groupId>\n" +
                    "            <artifactId>mariadb-java-client</artifactId>\n" +
                    "            <version>2.3.0</version>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <!-- Junit 单元测试-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>junit</groupId>\n" +
                    "            <artifactId>junit</artifactId>\n" +
                    "            <version>4.12</version>\n" +
                    "            <scope>test</scope>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <!--日志框架 logback-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>ch.qos.logback</groupId>\n" +
                    "            <artifactId>logback-classic</artifactId>\n" +
                    "            <version>1.2.3</version>\n" +
                    "            <scope>test</scope>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <!--其他-->\n" +
                    "        <!--server 接口 jar包-->\n" +
                    "        <dependency>\n" +
                    "            <groupId>javax.servlet</groupId>\n" +
                    "            <artifactId>javax.servlet-api</artifactId>\n" +
                    "            <version>4.0.1</version>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <dependency>\n" +
                    "            <groupId>com.google.code.gson</groupId>\n" +
                    "            <artifactId>gson</artifactId>\n" +
                    "            <version>2.8.5</version>\n" +
                    "        </dependency>\n" +
                    "\n" +
                    "        <dependency>\n" +
                    "            <groupId>javax.servlet</groupId>\n" +
                    "            <artifactId>jstl</artifactId>\n" +
                    "            <version>1.2</version>\n" +
                    "        </dependency>\n" +
                    "    </dependencies>\n" +
                    "\n" +
                    "    <build>\n" +
                    "        <plugins>\n" +
                    "            <plugin>\n" +
                    "                <groupId>org.mybatis.generator</groupId>\n" +
                    "                <artifactId>mybatis-generator-maven-plugin</artifactId>\n" +
                    "                <version>1.3.7</version>\n" +
                    "                <!--配置信息-->\n" +
                    "                <configuration>\n" +
                    "                    <!--配置文件-->\n" +
                    "                    <configurationFile>${basedir}/src/main/resources/generatorConfig.xml</configurationFile>\n" +
                    "                    <overwrite>true</overwrite>\n" +
                    "                </configuration>\n" +
                    "                <!--数据库的驱动的依赖-->\n" +
                    "                <dependencies>\n" +
                    "                    <dependency>\n" +
                    "                        <groupId>org.mariadb.jdbc</groupId>\n" +
                    "                        <artifactId>mariadb-java-client</artifactId>\n" +
                    "                        <version>2.3.0</version>\n" +
                    "                    </dependency>\n" +
                    "                </dependencies>\n" +
                    "            </plugin>\n" +
                    "\n" +
                    "        </plugins>\n" +
                    "    </build>\n" +
                    "\n" +
                    "\n" +
                    "</project>");

            out.write(new String(sbf).getBytes());
            out.flush();
            out.close();
            System.out.println("pom.xml 文件 配置成功，请重新刷新一下 maven项目");
        } catch (Exception e) {
            System.out.println("pom.xml 文件 配置失败,异常信息为：" + e.getMessage());
        }

    }

    //自动生成 generatorConfig.xml 文件
    public static void generate_generatorConfig() {
        File f = new File(projectPath + File.separator + "/src/main/resources/generatorConfig.xml");

        try {
            if (!f.isFile()) {
                f.createNewFile();
            }

            OutputStream out = new FileOutputStream(f);

            StringBuffer sbf = new StringBuffer();
            sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                    "<!DOCTYPE generatorConfiguration\n" +
                    "        PUBLIC \"-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN\"\n" +
                    "        \"http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd\">\n" +
                    "\n" +
                    "<generatorConfiguration>\n" +
                    "\n" +
                    "    <context id=\"default\" targetRuntime=\"MyBatis3Simple\">\n" +
                    "        <!--创建class时，对注释进行控制-->\n" +
                    "        <commentGenerator>\n" +
                    "            <property name=\"suppressDate\" value=\"true\" />\n" +
                    "            <!--去除注释-->\n" +
                    "            <property name=\"suppressAllComments\" value=\"true\"/>\n" +
                    "        </commentGenerator>\n" +
                    "\n" +
                    "        <!--jdbc的数据库连接-->\n" +
                    "        <jdbcConnection driverClass=\"" + drive + "\"\n" +
                    "                        connectionURL=\"" + url + "\"\n" +
                    "                        userId=\"" + user + "\" password=\"" + password + "\">\n" +
                    "        </jdbcConnection>\n" +
                    "        <!-- Model模型生成器\n" +
                    "            targetPackage -> 指定生成的model生成所在的包名\n" +
                    "            targetProject -> 指定在该项目下所在的路径\n" +
                    "        -->\n" +
                    "        <javaModelGenerator targetPackage=\"" + Package + ".entity\" targetProject=\"src/main/java\">\n" +
                    "            <!-- 是否对类CHAR类型的列的数据进行trim操作 -->\n" +
                    "            <property name=\"trimStrings\" value=\"true\" />\n" +
                    "        </javaModelGenerator>\n" +
                    "        <!--Mapper映射文件生成所在的目录 为每一个数据库的表生成对应的SqlMap文件-->\n" +
                    "        <sqlMapGenerator targetPackage=\"Mapper\"  targetProject=\"src/main/resources\"/>\n" +
                    "\n" +
                    "        <!-- 客户端代码，生成易于使用的针对Model对象和XML配置文件 的代码\n" +
                    "            type=\"ANNOTATEDMAPPER\",生成Java Model 和基于注解的Mapper对象\n" +
                    "            type=\"MIXEDMAPPER\",生成基于注解的Java Model 和相应的Mapper对象\n" +
                    "            type=\"XMLMAPPER\",生成SQLMap XML文件和独立的Mapper接口\n" +
                    "        -->\n" +
                    "        <javaClientGenerator type=\"XMLMAPPER\" targetPackage=\"" + Package + ".dao\"  targetProject=\"src/main/java\"/>\n" +
                    "\n" +
                    "        <!-- tableName 表名  % -> 全部表   -->\n" +
                    "        <table tableName=\"%\">\n" +
                    "            <generatedKey column=\"id\" sqlStatement=\"Mysql\"/>\n" +
                    "        </table>\n" +
                    "    </context>\n" +
                    "</generatorConfiguration>");

            out.write(new String(sbf).getBytes());
            out.flush();
            out.close();
            System.out.println("generatorConfig.xml 文件创建成功，请刷新一下maven项目");
        } catch (Exception e) {
            System.out.println("generatorConfig.xml 文件创建失败,异常信息为：" + e.getMessage());
        }

    }

    /**
     * 把输入字符串的首字母改成大写
     *
     * @param str
     * @return
     */
    private static String initcap(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'a' && ch[0] <= 'z') {
            ch[0] = (char) (ch[0] - 32);
        }
        return new String(ch);
    }

    /**
     * 把输入字符串的首字母改成小写
     *
     * @param str
     * @return
     */
    private static String initlow(String str) {
        char[] ch = str.toCharArray();
        if (ch[0] >= 'A' && ch[0] <= 'Z') {
            ch[0] = (char) (ch[0] + 32);
        }
        return new String(ch);
    }

    //首字母转换和下划线转换
    private static String tables(String table) {
        String[] tables = table.split("_");
        table = "";
        for (String s : tables) {
            table += initcap(s);
        }
        return table;
    }

    //获取 数据库中的表名
    private static List getTable() {
        List<String> table_name = new ArrayList<>();
        try {
            Class.forName(drive);
            Connection connection = DriverManager.getConnection(url, user, password);

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, null);

            while (tables.next()) {
                table_name.add(tables(tables.getString("TABLE_NAME")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return table_name;
    }

    //创建 service 层

    /**
     * @param tableName 表名
     */
    private static String generate_Service(String tableName) {

        StringBuffer sbf = new StringBuffer("package " + Package + ".service;\n" +
                "\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "public interface " + tableName + "Service {\n" +
                "\n" +
                "    /**\n" +
                "     * 删除操作 根据id\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    int deleteByPrimaryKey(Integer id);\n" +
                "\n" +
                "    /**\n" +
                "     * 添加操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    int insert(" + tableName + " " + initlow(tableName) + ");\n" +
                "\n" +
                "    /**\n" +
                "     * 根据id查询操作\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    " + tableName + " selectByPrimaryKey(Integer id);\n" +
                "\n" +
                "    /**\n" +
                "     * 全部查询操作\n" +
                "     *\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    List<" + tableName + "> selectAll();\n" +
                "\n" +
                "    /**\n" +
                "     * 修改操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */" +
                "\n" +
                "    int updateByPrimaryKey(" + tableName + " " + initlow(tableName) + ");\n" +
                "}");
        return new String(sbf);
    }

    /**
     * 创建ServiceImpl
     *
     * @param tableName 数据库表
     */
    private static String generate_ServiceImp(String tableName) {

        String serviceImpl = "package " + Package + ".service.impl;\n" +
                "\n" +
                "import " + Package + ".dao." + tableName + "Mapper;\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "import " + Package + ".service." + tableName + "Service;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n" +
                "\n" +
                "import java.util.List;\n" +
                "\n" +
                "@Service\n" +
                "public class " + tableName + "ServiceImpl implements " + tableName + "Service {\n" +
                "\n" +
                "    @Autowired\n" +
                "    private " + tableName + "Mapper " + initlow(tableName) + "Mapper;\n" +
                "\n" +
                "    /**\n" +
                "     * 删除操作 根据id删除\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int deleteByPrimaryKey(Integer id) {\n" +
                "        return " + initlow(tableName) + "Mapper.deleteByPrimaryKey(id);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 添加操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int insert(" + tableName + " " + initlow(tableName) + ") {\n" +
                "        return " + initlow(tableName) + "Mapper.insert(" + initlow(tableName) + ");\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据id查询操作\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public " + tableName + " selectByPrimaryKey(Integer id) {\n" +
                "        return " + initlow(tableName) + "Mapper.selectByPrimaryKey(id);\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 全部查询操作\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public List<" + tableName + "> selectAll() {\n" +
                "        return " + initlow(tableName) + "Mapper.selectAll();\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 修改操作\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @Override\n" +
                "    public int updateByPrimaryKey(" + tableName + " " + initlow(tableName) + ") {\n" +
                "        return " + initlow(tableName) + "Mapper.updateByPrimaryKey(" + initlow(tableName) + ");\n" +
                "    }\n" +
                "}\n";
        return serviceImpl;
    }

    /**
     * 创建Controller
     *
     * @param tableName 数据库表
     */
    private static String generate_Controller(String tableName) {
        String controller = "package " + Package + ".controller;\n" +
                "import " + Package + ".entity." + tableName + ";\n" +
                "import " + Package + ".service.impl." + tableName + "ServiceImpl;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.*;\n" +
                "import java.util.List;\n" +
                "\n" +
                "@RestController\n" +
                "@RequestMapping(\"/" + initlow(tableName) + "\")\n" +
                "public class " + tableName + "Controller {\n" +
                "    @Autowired\n" +
                "    private " + tableName + "ServiceImpl " + initlow(tableName) + "ServiceImpl;\n" +
                "\n" +
                "    /**\n" +
                "     * 根据id删除\n" +
                "     * 要求转入 id\n" +
                "     *\n" +
                "     * @param id\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/deleteByPrimaryKey/{id}\")\n" +
                "    public Object deleteByPrimaryKey(@PathVariable(\"id\") int id) {\n" +
                "        try {\n" +
                "\n" +
                "            return " + initlow(tableName) + "ServiceImpl.deleteByPrimaryKey(id) > 0 ? \"删除成功\" : \"删除失败\";\n" +
                "        } catch (Exception ex) {\n" +
                "            return \"出错,请重试！\";\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 添加对象" + initlow(tableName) + "\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @PostMapping(\"/insert\")\n" +
                "    public Object insert(@RequestBody " + tableName + " " + initlow(tableName) + ") {\n" +
                "        try {\n" +
                "            return " + initlow(tableName) + "ServiceImpl.insert(" + initlow(tableName) + ") > 0 ? \"添加成功！\" : \"添加失败！\";\n" +
                "        } catch (Exception ex) {\n" +
                "            return \"出错,请重试！\";\n" +
                "        }\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据id查找对象  最多只能返回一个对象\n" +
                "     *\n" +
                "     * @param id \n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/selectByPrimaryKey/{id}\")\n" +
                "    public Object selectByPrimaryKey(@PathVariable(\"id\") int id) {\n" +
                "        try {\n" +
                "            " + tableName + " " + initlow(tableName) + "1 = " + initlow(tableName) + "ServiceImpl.selectByPrimaryKey(id);\n" +
                "            if (" + initlow(tableName) + "1 == null) {\n" +
                "                return \"无数据\";\n" +
                "            } else {\n" +
                "                return " + initlow(tableName) + "1;\n" +
                "            }\n" +
                "        } catch (Exception ex) {\n" +
                "            return \"出错,请重试！\";\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 查询所有数据\n" +
                "     *\n" +
                "     * @return\n" +
                "     */\n" +
                "    @GetMapping(\"/selectAll\")\n" +
                "    public Object selectAll() {\n" +
                "        //public Result selectAll(@RequestParam(defaultValue = \"1\") int pageNum, @RequestParam(defaultValue = \"10\") int pageSize) {\n" +
                "        try {\n" +
                "            //分页\n" +
                "            //PageHelper.startPage(pageNum, pageSize);\n" +
                "            List<" + tableName + "> list = " + initlow(tableName) + "ServiceImpl.selectAll();\n" +
                "            if (list == null) {\n" +
                "                return \"无数据\";\n" +
                "            } else {\n" +
                "                // return new Result().success(list, " + initlow(tableName) + "Service.count(\"\"));\n" +
                "                return list;\n" +
                "            }\n" +
                "        } catch (Exception ex) {\n" +
                "            return \"出错,请重试！\";\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * 根据id修改全部字段\n" +
                "     *\n" +
                "     * @param " + initlow(tableName) + "\n" +
                "     * @return\n" +
                "     */\n" +
                "    @PutMapping(value = \"/updateByPrimaryKey\")\n" +
                "    public Object updateByPrimaryKey(@RequestBody " + tableName + " " + initlow(tableName) + ") {\n" +
                "        try {\n" +
                "            return " + initlow(tableName) + "ServiceImpl.updateByPrimaryKey(" + initlow(tableName) + ") > 0 ? \"修改成功\" : \"修改失败\";\n" +
                "        } catch (Exception ex) {\n" +
                "            return \"出错,请重试！\";\n" +
                "        }\n" +
                "\n" +
                "\n" +
                "    }\n" +
                "}\n";
        return controller;
    }

    //创建 service serviceImp Controller
    private static void create(File file, String context) {

        //获取文件
        File parent = file.getParentFile();
        //如果不是目录
        if (parent != null) {
            //创建目录
            parent.mkdirs();
        }
        try {
            //创建文件
            file.createNewFile();
            FileWriter fileWriter = null;
            try {
                fileWriter = new FileWriter(file);
                fileWriter.write(context);
                fileWriter.flush();
                fileWriter.close();
            } catch (IOException e) {

            }
        } catch (IOException e) {
            System.out.println("创建文件失败:" + e.getMessage());
        }
    }

    // 调用入口
    private static void start() {

        String path = projectPath + "/src/main/java/";
        String[] split = Package.split("\\.");
        for (String s : split) {
            path += s + "/";
        }
        List table = getTable();
        for (Object str : table) {
            create(new File(path + "service/" + str + "Service.java"), generate_Service((String) str));
            create(new File(path + "/service" + "/impl/" + str + "ServiceImpl.java"), generate_ServiceImp((String) str));
            create(new File(path + "controller/" + str + "Controller.java"), generate_Controller((String) str));
        }
    }

    //删除自动生成类
    private static void del_0() {
        String[] clazzName = Thread.currentThread().getStackTrace()[1].getClassName().split("\\.");

        String path = projectPath + "/src/main/java/";
        String[] split = Package.split("\\.");
        for (String s : split) {
            path += s + "/";
        }
        File f = new File(path + clazzName[3] + ".java");
        f.delete();
        System.out.println(clazzName[3] + ".java 文件已经删除....");
        String path1 = projectPath + "/src/main/resources/generatorConfig.xml";
        File f1 = new File(path1);
        f1.delete();
        del_build();
    }

    // 删除 pom.xml 中 build 节点
    private static void del_build(){
        String propertyPath = System.getProperty("user.dir");
        File f = new File(propertyPath + "/pom.xml" );

        try {
            InputStream in = new FileInputStream(f);
            byte[] arr = new byte[(int) f.length()];
            in.read(arr);
            in.close();

            String context = new String(arr);

            int i = context.indexOf("<build>");
            int ii = context.indexOf("</build>") + 8;

            String replace = context.replace(context.substring(i, ii), "");

            OutputStream out = new FileOutputStream(f);
            out.write(replace.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * ====================================下面是生成配置文件的=======================================================
     */

    //删除文件
    private static void del() {
        String path = System.getProperty("user.dir") + "/src/main/java/";

        String[] split = Package.split("\\.");
        for (String s : split) {
            path += s + "/";
        }
        String[] className = Thread.currentThread().getStackTrace()[1].getClassName().split("\\.");

        File f = new File(path + className[3] + ".java");
        f.delete();

    }

    //调用入口
    private static void start_1() {

        String path = System.getProperty("user.dir") + "/src/main/resources/";
        String path1 = System.getProperty("user.dir") + "/src/main/webapp/WEB-INF/web.xml";
        createFile(path + "jdbc.properties", new String(generate_jdbc()));
        createFile(path + "mybatis-config.xml", new String(generate_mybatisConfig()));
        createFile(path + "logback-config.xml", new String(generate_logBack()));
        createFile(path + "spring-root.xml", new String(generate_springRoot()));
        createFile(path + "spring-web.xml", new String(generate_springWeb()));
        createFile(path1, new String(generate_xml()));
    }

    private static void createFile(String path, String content) {
        File f = new File(path);
        try {
            f.createNewFile();
            OutputStream out = new FileOutputStream(f);
            out.write(content.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //自动生成 jdbc.properties 文件
    private static StringBuffer generate_jdbc() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("jdbc.driver=org.mariadb.jdbc.Driver\n" +
                "jdbc.url=" + url + " \n" +
                "jdbc.username=" + user + "\n" +
                "jdbc.password=" + password + "");
        return sbf;
    }

    //自动生成 web.xml 文件
    private static StringBuffer generate_xml() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<web-app xmlns=\"http://xmlns.jcp.org/xml/ns/javaee\"\n" +
                "         xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "         xsi:schemaLocation=\"http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd\"\n" +
                "         version=\"4.0\">\n" +
                "    <!--项目名-->\n" +
                "    <display-name>" + projectName + "</display-name>\n" +
                "\n" +
                "    <!--配置spring容器-->\n" +
                "    <context-param>\n" +
                "        <param-name>contextConfigLocation</param-name>\n" +
                "        <param-value>classpath:spring-root.xml</param-value>\n" +
                "    </context-param>\n" +
                "    <listener>\n" +
                "        <!--上下文环境侦听器-->\n" +
                "        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>\n" +
                "    </listener>\n" +
                "\n" +
                "    <!--配置springMVC容器-->\n" +
                "    <servlet>\n" +
                "        <servlet-name>webs</servlet-name>\n" +
                "        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>\n" +
                "        <init-param>\n" +
                "            <param-name>contextConfigLocation</param-name>\n" +
                "            <param-value>classpath:spring-web.xml</param-value>\n" +
                "        </init-param>\n" +
                "        <multipart-config>\n" +
                "            <!--上传文件的大小限制，比如下面表示 5 M-->\n" +
                "            <max-file-size>5242880</max-file-size>\n" +
                "            <!--一次表单提交中文件的大小限制，必须下面代表 10 M -->\n" +
                "            <max-request-size>10485760</max-request-size>\n" +
                "            <!-- 多大的文件会被自动保存到硬盘上。0 代表所有 -->\n" +
                "            <file-size-threshold>0</file-size-threshold>\n" +
                "        </multipart-config>\n" +
                "    </servlet>\n" +
                "    <!--将所有请求拦截下来，交给spring mvc 处理-->\n" +
                "    <servlet-mapping>\n" +
                "        <servlet-name>webs</servlet-name>\n" +
                "        <url-pattern>/</url-pattern>\n" +
                "    </servlet-mapping>\n" +
                "\n" +
                "    <!--汉字编码问题-->\n" +
                "    <filter>\n" +
                "        <filter-name>setEncoding</filter-name>\n" +
                "        <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>\n" +
                "        <init-param>\n" +
                "            <param-name>encoding</param-name>\n" +
                "            <param-value>UTF-8</param-value>\n" +
                "        </init-param>\n" +
                "    </filter>\n" +
                "    <filter-mapping>\n" +
                "        <filter-name>setEncoding</filter-name>\n" +
                "        <url-pattern>/*</url-pattern>\n" +
                "    </filter-mapping>\n" +
                "</web-app>");
        return sbf;
    }

    //自动生成 spring-root.xml 文件
    private static StringBuffer generate_springRoot() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:contxt=\"http://www.springframework.org/schema/context\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd\">\n" +
                "\n" +
                "    <!--扫描 service 包中所使用注解的类-->\n" +
                "    <contxt:component-scan base-package=\"" + Package + ".service.impl\"/>\n" +
                "\n" +
                "\n" +
                "    <!--加载jdbc资源文件-->\n" +
                "    <contxt:property-placeholder location=\"classpath:jdbc.properties\"/>\n" +
                "    <!--配置数据源-->\n" +
                "    <bean id=\"dataSource\" class=\"com.mchange.v2.c3p0.ComboPooledDataSource\">\n" +
                "        <!--加载数据库驱动-->\n" +
                "        <property name=\"driverClass\" value=\"${jdbc.driver}\"/>\n" +
                "        <!--连接的数据库字符串-->\n" +
                "        <property name=\"jdbcUrl\" value=\"${jdbc.url}\"/>\n" +
                "        <!--账号、密码-->\n" +
                "        <property name=\"user\" value=\"${jdbc.username}\"/>\n" +
                "        <property name=\"password\" value=\"${jdbc.password}\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "    <!--配置mybatis-->\n" +
                "    <bean id=\"sqlSessionFactory\" class=\"org.mybatis.spring.SqlSessionFactoryBean\">\n" +
                "        <!--实例化数据源-->\n" +
                "        <property name=\"dataSource\" ref=\"dataSource\"/>\n" +
                "        <!--加载mybatis的配置-->\n" +
                "        <property name=\"configLocation\" value=\"classpath:mybatis-config.xml\"/>\n" +
                "        <!--映射mapper文件-->\n" +
                "        <property name=\"mapperLocations\" value=\"classpath:Mapper/*.xml\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "    <!--简化调用-->\n" +
                "    <bean class=\"org.mybatis.spring.mapper.MapperScannerConfigurer\">\n" +
                "        <property name=\"sqlSessionFactoryBeanName\" value=\"sqlSessionFactory\"/>\n" +
                "        <!--加载使用的接口 -->\n" +
                "        <property name=\"basePackage\" value=\"" + Package + ".dao\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "</beans>");
        return sbf;
    }

    //自动生成 spring-web.xml 文件
    private static StringBuffer generate_springWeb() {

        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
                "       xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
                "       xmlns:contxt=\"http://www.springframework.org/schema/context\"\n" +
                "       xmlns:mvc=\"http://www.springframework.org/schema/mvc\"\n" +
                "       xsi:schemaLocation=\"http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd\">\n" +
                "\n" +
                "    <!--扫描web包中的注解-->\n" +
                "    <contxt:component-scan base-package=\"" + Package + ".controller\"/>\n" +
                "\n" +
                "    <!--启动 mvc  常用注解-->\n" +
                "    <mvc:annotation-driven ></mvc:annotation-driven>\n" +
                "\n" +
                "    <!--将所有静态资源交给server处理-->\n" +
                "    <mvc:default-servlet-handler/>\n" +
                "    \n" +
                "    \n" +
                "    <!--配置视图器-->\n" +
                "    <!--前后端分离，这里使用不到-->\n" +
                "    <bean class=\"org.springframework.web.servlet.view.InternalResourceViewResolver\">\n" +
                "        <property name=\"viewClass\" value=\"org.springframework.web.servlet.view.JstlView\"/>\n" +
                "        <!--前缀、后缀-->\n" +
                "        <property name=\"prefix\" value=\"/WEB-INF/jsp/\"/>\n" +
                "        <property name=\"suffix\" value=\".jsp\"/>\n" +
                "    </bean>\n" +
                "\n" +
                "</beans>");
        return sbf;
    }

    //自动生成 mybatis-config.xml 文件
    private static StringBuffer generate_mybatisConfig() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<!DOCTYPE configuration PUBLIC \"-//mybatis.org//DTD Config 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-config.dtd\">\n" +
                "\n" +
                "<configuration>\n" +
                "    <settings>\n" +
                "        <!-- 使用jdbc的getGeneratedKeys获取数据库自增主键值 -->\n" +
                "        <setting name=\"useGeneratedKeys\" value=\"true\" />\n" +
                "        <!-- 使用列别名替换列名 默认:true -->\n" +
                "        <setting name=\"useColumnLabel\" value=\"true\" />\n" +
                "        <!-- 开启驼峰命名转换:Table {create_time} -> Entity {createTime} -->\n" +
                "        <setting name=\"mapUnderscoreToCamelCase\" value=\"true\" />\n" +
                "    </settings>\n" +
                "    <!--配置分页插件-->\n" +
                "    <plugins>\n" +
                "        <plugin interceptor=\"com.github.pagehelper.PageInterceptor\" />\n" +
                "    </plugins>\n" +
                "\n" +
                "</configuration>");
        return sbf;
    }

    //自动生成 logback-config.xml 文件
    private static StringBuffer generate_logBack() {
        StringBuffer sbf = new StringBuffer();
        sbf.append("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<configuration >\n" +
                "    <property name=\"LOG_HOME\" value=\"tp/log\"/>\n" +
                "\n" +
                "    <appender name=\"STDOUT\" class=\"ch.qos.logback.core.ConsoleAppender\" >\n" +
                "        <!-- 输出的格式 -->\n" +
                "        <encoder class=\"ch.qos.logback.classic.encoder.PatternLayoutEncoder\">\n" +
                "            <!--<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{50}: %msg%n</pattern>-->\n" +
                "            <pattern>\n" +
                "                %msg%n\n" +
                "            </pattern>\n" +
                "\n" +
                "        </encoder>\n" +
                "    </appender>\n" +
                "\n" +
                "    <!-- 监控 哪些包中的方法调用 输出日志 -->\n" +
                "    <logger name=\"" + Package + ".dao\" level=\"DEBUG\" additivity=\"false\">\n" +
                "        <!--<level value=\"INFO\" />-->\n" +
                "        <appender-ref ref=\"STDOUT\" />\n" +
                "    </logger>\n" +
                "\n" +
                "    <root level=\"error\"  additivity=\"false\" >\n" +
                "        <appender-ref ref=\"STDOUT\" />\n" +
                "    </root>\n" +
                "</configuration>");
        return sbf;
    }

}