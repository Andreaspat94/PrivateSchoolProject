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
public class Profession {

    private int pid;
    private String pname;

    public Profession() {
    }

    public Profession(int pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    public Profession(String pname) {
        this.pname = pname;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.pid;
        hash = 59 * hash + Objects.hashCode(this.pname);
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
        final Profession other = (Profession) obj;
        if (this.pid != other.pid) {
            return false;
        }
        if (!Objects.equals(this.pname, other.pname)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Profession_Id: " + pid + ", Profession_Title: " + pname + '}';
    }
}
