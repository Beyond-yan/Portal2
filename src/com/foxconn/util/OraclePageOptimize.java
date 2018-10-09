package com.foxconn.util;

public class OraclePageOptimize {
    public String getOptimizeSQLString(Page page, String sql, String key) {
        int pageNo = page.getPageIndex();
        int pageItems = page.getPageSize();
        String sFromItem = String.valueOf((pageNo - 1) * pageItems + 1);
        String sToItem = String.valueOf(pageNo * pageItems + 1);
        String strSqlQuery;
        
        strSqlQuery = "SELECT * FROM (SELECT ROWNUM AS MY_ROWNUM,TABLE_A.* FROM(" + sql;
        strSqlQuery = strSqlQuery + ") TABLE_A WHERE ROWNUM<" + sToItem;
        strSqlQuery = strSqlQuery + ") WHERE MY_ROWNUM>=" + sFromItem;

        return strSqlQuery;
    }
}
