package com.google.sparkproject.dao;

import com.google.sparkproject.dao.impl.SessionAggrStatDAOImpl;
import com.google.sparkproject.dao.impl.SessionDetailDAOImpl;
import com.google.sparkproject.dao.impl.SessionRandomExtractDAOImpl;
import com.google.sparkproject.dao.impl.TaskDAOImpl;

public class DAOFactory {
    public static ITaskDAO getTaskDAO() {
        return new TaskDAOImpl();
    }

    public static ISessionAggrStatDAO getSessionAggrStatDAO() {
        return new SessionAggrStatDAOImpl();
    }

    public static ISessionDetailDAO getSessionDetailDAO() { return new SessionDetailDAOImpl(); }

    public static ISessionRandomExtractDAO getSessionRandomExtractDAO() { return new SessionRandomExtractDAOImpl(); }

}


