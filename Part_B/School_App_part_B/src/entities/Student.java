/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author George
 */
public class Student {

    private int sid;
    private String sfname;
    private String slname;
    private int sfees;
    private LocalDate sdob;

    public Student() {
    }

    public Student(String sfname, String slname, int sfees, LocalDate sdob) {
        this.sfname = sfname;
        this.slname = slname;
        this.sfees = sfees;
        this.sdob = sdob;
    }

    public Student(int sid, String sfname, String slname, int sfees, LocalDate sdob) {
        this.sid = sid;
        this.sfname = sfname;
        this.slname = slname;
        this.sfees = sfees;
        this.sdob = sdob;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getSfname() {
        return sfname;
    }

    public void setSfname(String sfname) {
        this.sfname = sfname;
    }

    public String getSlname() {
        return slname;
    }

    public void setSlname(String slname) {
        this.slname = slname;
    }

    public int getSfees() {
        return sfees;
    }

    public void setSfees(int sfees) {
        this.sfees = sfees;
    }

    public LocalDate getSdob() {
        return sdob;
    }

    public void setSdob(LocalDate sdob) {
        this.sdob = sdob;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.sid;
        hash = 53 * hash + Objects.hashCode(this.sfname);
        hash = 53 * hash + Objects.hashCode(this.slname);
        hash = 53 * hash + this.sfees;
        hash = 53 * hash + Objects.hashCode(this.sdob);
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
        final Student other = (Student) obj;
        if (this.sid != other.sid) {
            return false;
        }
        if (this.sfees != other.sfees) {
            return false;
        }
        if (!Objects.equals(this.sfname, other.sfname)) {
            return false;
        }
        if (!Objects.equals(this.slname, other.slname)) {
            return false;
        }
        if (!Objects.equals(this.sdob, other.sdob)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student_Id=" + sid + ", First_Name: " + sfname + ", Last_Name: " + slname + ", Tuition_Fees: " + sfees + ", Date_Of_Birth: " + sdob + '}';
//      
    }

}
