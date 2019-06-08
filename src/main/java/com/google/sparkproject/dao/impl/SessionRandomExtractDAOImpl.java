package com.google.sparkproject.dao.impl;

import com.google.sparkproject.dao.ISessionRandomExtractDAO;
import com.google.sparkproject.domian.SessionRandomExtract;
import com.google.sparkproject.jdbc.JDBCHelper;

public class SessionRandomExtractDAOImpl implements ISessionRandomExtractDAO {

    public void insert(SessionRandomExtract sessionRandomExtract) {
        String sql = "insert into session_random_extract values(?,?,?,?,?)";

        Object[] params = new Object[]{
                sessionRandomExtract.getTaskid(),
                sessionRandomExtract.getSessionid(),
                sessionRandomExtract.getStartTime(),
                sessionRandomExtract.getSearchKeywords(),
                sessionRandomExtract.getClickCategoryIds()};

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeUpdate(sql, params);
    }


}
