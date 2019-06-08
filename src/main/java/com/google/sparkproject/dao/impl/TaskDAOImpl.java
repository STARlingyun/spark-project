package com.google.sparkproject.dao.impl;

import com.google.sparkproject.dao.ITaskDAO;
import com.google.sparkproject.domian.Task;
import com.google.sparkproject.jdbc.JDBCHelper;

public class TaskDAOImpl implements ITaskDAO {

    public Task findById(long taskid) {
        final Task task = new Task();

        String sql = "select * from task where task_id=?";
        Object[] params = new Object[]{taskid};

        JDBCHelper jdbcHelper = JDBCHelper.getInstance();
        jdbcHelper.executeQuery(sql,rs -> {
            if(rs.next()) {
                long taskid1 = rs.getLong(1);
                String taskName = rs.getString(2);
                String createTime = rs.getString(3);
                String startTime = rs.getString(4);
                String finishTime = rs.getString(5);
                String taskType = rs.getString(6);
                String taskStatus = rs.getString(7);
                String taskParam = rs.getString(8);

                task.setTaskid(taskid1);
                task.setTaskName(taskName);
                task.setCreateTime(createTime);
                task.setStartTime(startTime);
                task.setFinishTime(finishTime);
                task.setTaskType(taskType);
                task.setTaskStatus(taskStatus);
                task.setTaskParam(taskParam);
            }
        }, params);

        return task;
    }

}
