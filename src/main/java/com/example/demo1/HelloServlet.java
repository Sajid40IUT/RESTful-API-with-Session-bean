package com.example.demo1;

import java.io.*;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private String list = "";

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");

        String url="jdbc:mysql://localhost:3306/PCget";
        String username="root";
        String password="Kronos7988";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT Name FROM ComputerList");

            while (resultSet.next()){
                list = list + resultSet.getString(1);
            }

            connection.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }

        out.println("<h1>" + list + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}