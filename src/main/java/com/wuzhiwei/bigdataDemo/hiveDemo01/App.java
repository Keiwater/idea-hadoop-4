package com.wuzhiwei.bigdataDemo.hiveDemo01;

import java.sql.*;

public class App {

    private static String drvierName = "org.apache.hive.jdbc.HiveDriver";
    private static String url = "jdbc:hive2://172.16.10.188:10000/ti?characterEncoding=gbk";
    private static String user = "hive";
    private static String password = "bigdatahexin20180821";



    public static void main(String[] args) throws  Exception{
        Class.forName(drvierName);
        Connection conn  =DriverManager.getConnection(url,user,password);



        System.out.println(conn);

        Statement sta = conn.createStatement();

        String sql = "select * from ti_loa.ti_loa_evt_acc_se_bindbanknorecord limit 10";
        sql = "select count(*) as cn from ti_loa.ti_loa_evt_acc_se_bindbanknorecord";

        ResultSet res = sta.executeQuery(sql);

        while(res.next()){
            System.out.println(res.getInt("cn"));
        }



        DatabaseMetaData dbmd = conn.getMetaData();
//        ResultSet rs = dbmd.getColumns(conn.getCatalog(), schema, tableName, null);
//        rs.getString(DATA_TYPE); // java.sql.Types 的 SQL 类型
//        rs.getString(COLUMN_SIZE) ;//列的大小。对于 char 或 date 类型，列的大小是最大字符数，对于 numeric 和 decimal 类型，列的大小就是精度。
//        rs.getString(DECIMAL_DIGITS);




        String columnName;
        String columnType;
//        String tableName;

        String catalog = conn.getCatalog(); //catalog 其实也就是数据库名
        ResultSet tablesResultSet = dbmd.getTables(catalog,null,null,new String[]{"TABLE"});
        while(tablesResultSet.next()){
            String tableName = tablesResultSet.getString("TABLE_NAME");
            System.out.println(tableName);

//            // 获取列信息
//            ResultSet colRet = dbmd.getColumns(null,"%", tableName,"%");
//            while(colRet.next()) {
//                columnName = colRet.getString("COLUMN_NAME");
//                columnType = colRet.getString("TYPE_NAME");
//                int column_size = colRet.getInt("COLUMN_SIZE");
//                int digits = colRet.getInt("DECIMAL_DIGITS");
//                int nullable = colRet.getInt("NULLABLE");
//                System.out.println(columnName+" "+columnType+" "+column_size+" "+" "+
//                        nullable);



            tableName=tablesResultSet.getString("TABLE_NAME");
            String remarkes = tablesResultSet.getString("REMARKS");
            System.out.println(tableName+"="+remarkes);












            System.out.println("+++++++++++++++++++++++++++++");
        }




        res.close();
        sta.close();
        conn.close();



    }
}
