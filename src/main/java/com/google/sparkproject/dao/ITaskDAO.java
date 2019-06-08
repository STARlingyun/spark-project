package com.google.sparkproject.dao;

import com.google.sparkproject.domian.Task;

public interface ITaskDAO {
    Task findById(long taskid);
}
