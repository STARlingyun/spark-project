package com.google.sparkproject.jdbc;

import com.google.sparkproject.conf.ConfigurationManager;
import com.google.sparkproject.constants.Constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class JDBCHelper {

    static {
        try{
            String driver = ConfigurationManager.getProperty(
                    Constants.JDBC_DRIVER);
            Class.forName(driver);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static JDBCHelper instance = null;

    /**
     * 获取单例
     *
     * @return单例
     */
    public static JDBCHelper getInstance(){
        if(instance == null)
            synchronized (JDBCHelper.class){
                if (instance ==null){
                    instance = new JDBCHelper();
                }
            }
        return instance;
    }

    private LinkedList<Connection> datasource = new LinkedList<Connection>();

    private JDBCHelper(){
        int datasourceSize = Integer.parseInt(
                ConfigurationManager.getProperty(
                        Constants.JDBC_DATASOURCE_SIZE));

        for(int i = 0;i< datasourceSize; i++){
            String url = ConfigurationManager.getProperty(Constants.JDBC_URL);
            String user = ConfigurationManager.getProperty(Constants.JDBC_USER);
            String password = ConfigurationManager.getProperty(Constants.JDBC_PASSWORD);
            try{
                Connection conn = DriverManager.getConnection(url,user,password);
                datasource.push(conn);
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public synchronized Connection getConnection(){
        while(datasource.size() == 0){
            try{
                Thread.sleep(10);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return datasource.poll();
    }

    public int executeUpdate(String sql,Object... params){
        int rtn = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            for(int i =0;i<params.length;i++){
                pstmt.setObject(i + 1,params[i]);
            }
            rtn = pstmt.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(conn != null){
                datasource.push(conn);
            }
        }
        return rtn;
    }

    public void executeQuery(String sql,QueryCallback callback,Object[] params){
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
         try{
             conn = getConnection();
             pstmt = conn.prepareStatement(sql);
             for (int i = 0; i < params.length; i++) {
                 pstmt.setObject(i + 1, params[i]);
             }
             rs = pstmt.executeQuery();
             callback.process(rs);
         }catch (Exception e){
             e.printStackTrace();
         }finally {
             if(conn != null){
                 datasource.push(conn);
             }
         }

    }

//    批量数据的插入

    public int[] executeBatch(String sql, List<Object[]> paramsList) {
        int[] rtn = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();

            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            for (Object[] params : paramsList) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                pstmt.addBatch();
            }

            rtn = pstmt.executeBatch();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rtn;
    }



    public static interface QueryCallback {
        void process(ResultSet rs) throws Exception;
    }




}
