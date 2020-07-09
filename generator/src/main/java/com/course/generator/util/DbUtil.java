package com.course.generator.util;

import com.course.generator.enums.EnumGenerator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DbUtil {

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/courseimooc";
            String user = "courseimooc";
            String pass = "courseimooc";
            conn = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * 获得表注释
     * @param tableName
     * @return
     * @throws Exception
     */
    public static String getTableComment(String tableName) throws Exception {
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select table_comment from information_schema.tables Where table_name = '" + tableName + "'");
        String tableNameCH = "";
        if (rs != null) {
            while(rs.next()) {
                tableNameCH = rs.getString("table_comment");
                break;
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("表名：" + tableNameCH);
        return tableNameCH;
    }

    /**
     * 获得所有列信息
     * @param tableName
     * @return
     * @throws Exception
     */
    public static List<Field> getColumnByTableName(String tableName) throws Exception {
        List<Field> fieldList = new ArrayList<>();
        Connection conn = getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("show full columns from `" + tableName + "`");
        if (rs != null) {
            while(rs.next()) {
                String columnName = rs.getString("Field");
                String type = rs.getString("Type");
                String comment = rs.getString("Comment");
                String nullAble = rs.getString("Null"); //YES NO
                Field field = new Field();
                field.setName(columnName);
                field.setNameHump(lineToHump(columnName));
                field.setNameBigHump(lineToBigHump(columnName));
                field.setType(type);
                field.setJavaType(DbUtil.sqlTypeToJavaType(rs.getString("Type")));
                field.setComment(comment);
                if (comment.contains("|")) {
                    field.setNameCn(comment.substring(0, comment.indexOf("|")));
                } else {
                    field.setNameCn(comment);
                }
                field.setNullAble("YES".equals(nullAble));
                if (type.toUpperCase().contains("varchar".toUpperCase())) {
                    String lengthStr = type.substring(type.indexOf("(") + 1, type.length() - 1);
                    field.setLength(Integer.valueOf(lengthStr));
                } else {
                    field.setLength(0);
                }
                if (comment.contains("枚举")) {
                    field.setEnums(true);

                    // 以课程等级为例：从注释中的“枚举[CourseLevelEnum]”，得到COURSE_LEVEL
                    int start = comment.indexOf("[");
                    int end = comment.indexOf("]");
                    String enumsName = comment.substring(start + 1, end);
                    String enumsConst = EnumGenerator.toUnderline(enumsName);
                    field.setEnumsConst(enumsConst);
                } else {
                    field.setEnums(false);
                }
                fieldList.add(field);
            }
        }
        rs.close();
        stmt.close();
        conn.close();
        System.out.println("列信息：" + fieldList);
        return fieldList;
    }

    /**
     * 下划线转小驼峰
     */
    public static String lineToHump(String str){
        Pattern linePattern = Pattern.compile("_(\\w)");
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 下划线转大驼峰
     */
    public static String lineToBigHump(String str){
        String s = lineToHump(str);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    /**
     * 数据库类型转为Java类型
     */
    public static String sqlTypeToJavaType(String sqlType) {
        if (sqlType.toUpperCase().contains("varchar".toUpperCase())
                || sqlType.toUpperCase().contains("char".toUpperCase())
                || sqlType.toUpperCase().contains("text".toUpperCase())) {
            return "String";
        } else if (sqlType.toUpperCase().contains("datetime".toUpperCase())) {
            return "Date";
        } else if (sqlType.toUpperCase().contains("int".toUpperCase())) {
            return "Integer";
        } else if (sqlType.toUpperCase().contains("long".toUpperCase())) {
            return "Long";
        } else if (sqlType.toUpperCase().contains("decimal".toUpperCase())) {
            return "BigDecimal";
        } else {
            return "String";
        }
    }
}
