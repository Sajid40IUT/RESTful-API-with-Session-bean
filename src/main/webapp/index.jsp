<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="connection.DbConnection" %>
<%@ page import="bean.studentBean" %>
<%@page import="java.util.*"%>
<%@ page import="Dao.StudentDao" %>

<%
StudentDao sd = new StudentDao(DbConnection.getConnection());
List<studentBean> students = sd.getAllStudents();
%>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>


    <div class="container">

            <%
                if ( !students.isEmpty() )
                {
                    for (studentBean p:students){ %>
                    <div class="col-md-3 my-3">
                        <div>
                                <h5 >id: <%= p.getStdID() %></h5>
                                <h6 >name: <%= p.getName() %></h6>
                                <h6 >Semester: <%= p.getSemester() %></h6>
                                <h6 >CGPA: <%= p.getCgpa() %></h6>
                        </div>
                    </div>
                    <%}
                }
            %>


        </div>
    </div>

</body>
</html>