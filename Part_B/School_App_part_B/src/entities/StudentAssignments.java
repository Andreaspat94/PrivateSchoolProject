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
public class StudentAssignments {

    private int said;
    private Student st;
    private Assignment assignment;
    private Course c;
    private int mark;

    public StudentAssignments() {
    }

    public StudentAssignments(int said, Student st, Assignment assignment, Course c, int mark) {
        this.said = said;
        this.st = st;
        this.assignment = assignment;
        this.c = c;
        this.mark = mark;
    }

    public StudentAssignments(Student st, Assignment assignment, Course c, int mark) {
        this.st = st;
        this.assignment = assignment;
        this.c = c;
        this.mark = mark;
    }

    public int getSaid() {
        return said;
    }

    public void setSaid(int said) {
        this.said = said;
    }

    public Student getSt() {
        return st;
    }

    public void setSt(Student st) {
        this.st = st;
    }

    public Assignment getAssignment() {
        return assignment;
    }

    public void setAssignment(Assignment assignment) {
        this.assignment = assignment;
    }

    public Course getC() {
        return c;
    }

    public void setC(Course c) {
        this.c = c;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.said;
        hash = 79 * hash + Objects.hashCode(this.st);
        hash = 79 * hash + Objects.hashCode(this.assignment);
        hash = 79 * hash + Objects.hashCode(this.c);
        hash = 79 * hash + this.mark;
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
        final StudentAssignments other = (StudentAssignments) obj;
        if (this.said != other.said) {
            return false;
        }
        if (this.mark != other.mark) {
            return false;
        }
        if (!Objects.equals(this.st, other.st)) {
            return false;
        }
        if (!Objects.equals(this.assignment, other.assignment)) {
            return false;
        }
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Id: " + said + ", " + st + ", " + assignment + ", " + c + ", Mark: " + mark + '}';
    }

}
