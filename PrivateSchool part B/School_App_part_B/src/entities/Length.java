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
public class Length {

    private int lid;
    private String lname;

    public Length() {
    }

    public Length(int lid, String lname) {
        this.lid = lid;
        this.lname = lname;
    }

    public Length(String lname) {
        this.lname = lname;
    }

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.lid;
        hash = 29 * hash + Objects.hashCode(this.lname);
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
        final Length other = (Length) obj;
        if (this.lid != other.lid) {
            return false;
        }
        if (!Objects.equals(this.lname, other.lname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Length_Id: " + lid + ", Length_name: " + lname + '}';
    }

}
