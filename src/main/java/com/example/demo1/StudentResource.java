package com.example.demo1;


import Dao.StudentDao;
import bean.studentBean;
import connection.DbConnection;

import javax.ejb.Stateless;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.sql.SQLException;
import java.util.List;

@Stateless
@Path("/student")
public class StudentResource {

    StudentDao sd = new StudentDao(DbConnection.getConnection());
    List<studentBean> students = sd.getAllStudents();


    public StudentResource() throws SQLException, ClassNotFoundException {
    }

    @GET
    @Path("/student-list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<studentBean> getStudents()
    {
        return students;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public String getStudentById(@PathParam("id") int studentId)
    {
        String studentById = sd.getName(studentId);
        return studentById;
    }


    @POST
    @Path("/add-student")
    public String addStudents(@QueryParam("id") int id,
                              @QueryParam("name") String name,
                              @QueryParam("semester") int semester,
                              @QueryParam("cgpa") double cgpa){

        sd.addStudent(id, name, semester, cgpa);
        return "student added";
    }

    @POST
    @Path("/change-name")
    public String addStudents(@QueryParam("id") int id,
                              @QueryParam("name") String name){

        sd.changeName(id, name);
        return "student Name Updated";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/higher-cg")
    public List<studentBean> getHigerCgStudent(@QueryParam("id1") int id1,
                                               @QueryParam("id2") int id2) {

        List<studentBean> studentWithHigherCG = sd.getStudentHigherCg(id1, id2);
        return studentWithHigherCG;
    }

}
