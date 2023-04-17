package Dao;

import bean.studentBean;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.*;
public class StudentDao {
    private Connection con;

    private String query;
    private PreparedStatement pst;
    private ResultSet rs;

    public StudentDao(Connection con) {
        super();
        this.con = con;
    }

    public List<studentBean> getAllStudents() {
        List<studentBean> students = new ArrayList<studentBean>();
        try {

            query = "SELECT * FROM student";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                studentBean row = new studentBean();
                row.setStdID(rs.getInt("studentId"));
                row.setName(rs.getString("name"));
                row.setSemester(rs.getInt("semester"));
                row.setCgpa(rs.getDouble("cgpa"));

                students.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return students;
    }

    public String getName(int id) {
        String name = "";
        try {

            query = "SELECT name FROM student WHERE studentId = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                name = name.concat(rs.getString(1));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return name;
    }

    public  void addStudent(int id, String name, int semester, double cgpa) {
        try {
            query = "INSERT INTO student (studentId, name, semester, cgpa) VALUES (?, ?, ?, ?)";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            pst.setString(2, name);
            pst.setInt(3, semester);
            pst.setDouble(4, cgpa);

            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void changeName(int id, String name) {
        try {
            query = "UPDATE students SET name = ? WHERE id = ?";
            pst = this.con.prepareStatement(query);
            pst.setString(1, name);
            pst.setInt(2, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public List<studentBean> getStudentHigherCg(int id1, int id2) {
        List<studentBean> students = new ArrayList<studentBean>();

        double cg1 = 0;
        double cg2 = 0;
        int id;

        try {

            query = "SELECT cgpa FROM student WHERE studentId = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id1);
            rs = pst.executeQuery();

            while (rs.next()) {
                cg1 = cg1 + rs.getDouble(1);
            }

            query = "SELECT cgpa FROM student WHERE studentId = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id2);
            rs = pst.executeQuery();

            while (rs.next()) {
                cg2 = cg2 + rs.getDouble(1);
            }

            if (cg1 > cg2) {
                id = id1;
            } else {
                id = id2;
            }

            query = "SELECT * FROM student WHERE studentId = ?";
            pst = this.con.prepareStatement(query);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            while (rs.next()) {
                studentBean row = new studentBean();
                row.setStdID(rs.getInt("studentId"));
                row.setName(rs.getString("name"));
                row.setCgpa(rs.getDouble("cgpa"));

                students.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return students;
    }

    public List<studentBean> getHighestCgpa() {
        List<studentBean> students = new ArrayList<studentBean>();
        try {

            query = "SELECT * FROM student WHERE cgpa = (SELECT MAX(cgpa) FROM student)";
            pst = this.con.prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                studentBean row = new studentBean();
                row.setStdID(rs.getInt("studentId"));
                row.setName(rs.getString("name"));
                row.setSemester(rs.getInt("semester"));
                row.setCgpa(rs.getDouble("cgpa"));

                students.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return students;
    }

}
