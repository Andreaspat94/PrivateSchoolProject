/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Objects;

/**
 *
 * @author George
 */
public class StudentsPerCourse {

    private int scid;
    private Student student;
    private Course course;

    public StudentsPerCourse() {
    }

    public StudentsPerCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public StudentsPerCourse(int scid, Student student, Course course) {
        this.scid = scid;
        this.student = student;
        this.course = course;
    }

    public int getScid() {
        return scid;
    }

    public void setScid(int scid) {
        this.scid = scid;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.scid;
        hash = 17 * hash + Objects.hashCode(this.student);
        hash = 17 * hash + Objects.hashCode(this.course);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentsPerCourse other = (StudentsPerCourse) obj;
        if (this.scid != other.scid) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id: " + scid + ", " + student + ", " + course + '}';
    }

}
