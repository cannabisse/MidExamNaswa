package com.example.midexam;

public class StudentModel {
    Integer id;
    String studentId, name;
    boolean enrolled;

    public StudentModel(String studentId, String name, boolean enrolled) {
        this.studentId = studentId;
        this.name = name;
        this.enrolled = enrolled;
    }

    public StudentModel(Integer id, String studentId, String name, boolean enrolled) {
        this.id = id;
        this.studentId = studentId;
        this.name = name;
        this.enrolled = enrolled;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnrolled() {
        return this.enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }
}
