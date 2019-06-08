package com.google.sparkproject.dao.impl;

import com.google.sparkproject.dao.ISessionDetailDAO;
import com.google.sparkproject.domian.SessionDetail;
import com.google.sparkproject.jdbc.JDBCHelper;

public class SessionDetailDAOImpl implements ISessionDetailDAO {

    public void insert(SessionDetail sessionDetail) {
        String sql = "insert into session_detail values(?,?,?,?,?,?,?,?,?,?,?,?)";


        Object[] params = new Object[]{
                sessionDetail.getTaskid(),
                sessionDetail.getUserid(),
                sessionDetail.getSessionid(),
                sessionDetail.getPageid(),
                sessionDetail.getActionTime(),
                sessionDetail.getSearchKeyword(),
                sessionDetail.getClickCategoryId(),
                sessionDetail.getClickProductId(),
                sessionDetail.getOrderCategoryIds(),
                sessionDetail.getOrderProductIds(),
                sessionDetail.getPayCategoryIds(),
                sessionDetail.getPayProductIds()
        };

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
            jdbcHelper.executeUpdate(sql, params);
    }


}

