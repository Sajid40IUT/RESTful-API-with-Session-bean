package bean;

import java.io.Serializable;

public class studentBean implements Serializable {

    private int stdID;
    private String name;
    private int semester;
    private double cgpa;

    public studentBean() {
    }

    public studentBean(int stdID, String name, int semester, double cgpa) {
        this.stdID = stdID;
        this.name = name;
        this.semester = semester;
        this.cgpa = cgpa;
    }

    public int getStdID() {
        return stdID;
    }

    public void setStdID(int stdID) {
        this.stdID = stdID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public double getCgpa() {
        return cgpa;
    }

    public void setCgpa(double cgpa) {
        this.cgpa = cgpa;
    }
}
