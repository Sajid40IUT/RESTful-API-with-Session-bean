package com.example.demo1;

import javax.ejb.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import Dao.StudentDao;
import bean.studentBean;
import java.sql.SQLException;
import java.util.List;

import connection.DbConnection;

@Singleton
@Path("/highest-cgpa")
public class SingletonDemo {

    StudentDao sd = new StudentDao(DbConnection.getConnection());

    public SingletonDemo() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<studentBean> getHighestCGPAStudent() {
        return sd.getHighestCgpa();
    }
}
