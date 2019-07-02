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
public class AssignmentsPerCourse {

    private int acid;
    private Course course;
    private Assignment assignment;

    public AssignmentsPerCourse() {
    }

    public AssignmentsPerCourse(Course course, Assignment assignment) {
        this.course = course;
        this.assignment = assignment;
    }

    public AssignmentsPerCourse(int acid, Course course, Assignment assignment) {
        this.acid = acid;
        this.course = course;
        this.assignment = assignment;
    }

    public int getAcid() {
        return acid;
    }

    public void setAcid(int acid) {
        this.acid = acid;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 13 * hash + this.acid;
        hash = 13 * hash + Objects.hashCode(this.course);
        hash = 13 * hash + Objects.hashCode(this.assignment);
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
        final AssignmentsPerCourse other = (AssignmentsPerCourse) obj;
        if (this.acid != other.acid) {
            return false;
        }
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id: " + acid + ", " + course + ", " + assignment + '}';
    }

}
